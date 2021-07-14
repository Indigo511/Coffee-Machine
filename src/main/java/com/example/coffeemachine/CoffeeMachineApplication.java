package com.example.coffeemachine;

import com.example.coffeemachine.common.model.*;
import com.example.coffeemachine.common.request.MachineRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class CoffeeMachineApplication {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(CoffeeMachineApplication.class, args);
        request();
    }

    public static void request() {
        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            MachineRequest request = objectMapper.readValue
                    (new FileReader("src/main/resources/sample-request.json"), MachineRequest.class);
            IngredientInventory ingredientInventory = new IngredientInventory();
            Mixer mixer = new Mixer(ingredientInventory);
            request.getMaxCapacity().forEach(ingredientInventory::addIngredient);
            request.getBeverages().forEach(coffee -> {
                coffee.setMixer(mixer);
                ingredientInventory.addDrink(CoffeeFactory.getCoffee(coffee));
            });
            serveCoffee(ingredientInventory);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static void serveCoffee(final IngredientInventory ingredientInventory) {
        while (true) {
            log.info("Available Choices: ");

            log.info("Capacity {}", ingredientInventory.getIngredientInventoryMap());

            log.info("|   Select Type:   |\n | 1:   Cappuccino |\n| 2:  Americano  |\n| 3:  Latte  |\n| 4:  Espresso  |\n| 5:  Mocha  |\n| 0   to Discard   |");
            int t = scan.nextInt();
            Map<Integer, String> coffeeMap = new HashMap<>();
            coffeeMap.put(1, CoffeeTypes.CAPPUCCINO.name());
            coffeeMap.put(2, CoffeeTypes.AMERICANO.name());
            coffeeMap.put(3, CoffeeTypes.LATTE.name());
            coffeeMap.put(4, CoffeeTypes.ESPRESSO.name());
            coffeeMap.put(5, CoffeeTypes.MOCHA.name());
            String coffeeName = coffeeMap.getOrDefault(t, CoffeeTypes.CAPPUCCINO.name());

            makeCoffee(ingredientInventory, coffeeName);
            log.info("Coffee is served {}", coffeeName);

            log.info("Do you want to exit if yes then press Y else N");
            char exit = scan.next().charAt(0);
            if (exit == 'Y') break;
        }
    }

    public static void makeCoffee(final IngredientInventory ingredientInventory, final String coffeeName) {
        ingredientInventory.getDrinks().stream().filter(drink ->
                drink.getName().equals(coffeeName)).findFirst().get().process();
    }
}
