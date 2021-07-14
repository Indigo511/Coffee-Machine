package com.example.coffeemachine;

import com.example.coffeemachine.common.model.CoffeeFactory;
import com.example.coffeemachine.common.model.CoffeeTypes;
import com.example.coffeemachine.common.model.IngredientInventory;
import com.example.coffeemachine.common.model.Mixer;
import com.example.coffeemachine.common.request.MachineRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class CoffeeMachineApplicationTests {
    private IngredientInventory ingredientInventory = new IngredientInventory();
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    public void setInventory() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MachineRequest request = new ObjectMapper().readValue
                    (new FileReader("src/test/resources/sample-request.json"), MachineRequest.class);
            Mixer mixer = new Mixer(ingredientInventory);
            request.getMaxCapacity().forEach(ingredientInventory::addIngredient);
            request.getBeverages().forEach(coffee -> {
                coffee.setMixer(mixer);
                ingredientInventory.addDrink(CoffeeFactory.getCoffee(coffee));
            });
        } catch (Exception e) {

        }
    }

    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testInventory() {
        setInventory();
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(200); // add this quantity of ingredient
        when(mockScanner.next()).thenReturn("Y"); // add ingredient
        ingredientInventory.setScanner(mockScanner);

        CoffeeMachineApplication.makeCoffee(ingredientInventory, CoffeeTypes.CAPPUCCINO.name());
        log.info("{}", ingredientInventory.getIngredientInventoryMap());

        CoffeeMachineApplication.makeCoffee(ingredientInventory, CoffeeTypes.AMERICANO.name());
        assertEquals(200, ingredientInventory.getIngredientInventoryMap().get("coffee"));

        when(mockScanner.next()).thenReturn("N");
        CoffeeMachineApplication.makeCoffee(ingredientInventory, CoffeeTypes.AMERICANO.name());
        assertEquals(0, ingredientInventory.getIngredientInventoryMap().get("coffee"));

        log.info("{}", ingredientInventory.getIngredientInventoryMap());

        try {
            CoffeeMachineApplication.makeCoffee(ingredientInventory, CoffeeTypes.LATTE.name());
        } catch (Exception e) {
            log.info("Insufficient amount of coffee", e);
        }


    }

}
