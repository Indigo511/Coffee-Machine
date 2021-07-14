package com.example.coffeemachine.common.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class IngredientInventory {

    private Map<String, Integer> ingredientInventoryMap = new HashMap<>();
    private List<Drink> drinks = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public void addIngredient(String ingredientName, int quantity) {
        ingredientInventoryMap.put(ingredientName, quantity +
                ingredientInventoryMap.getOrDefault(ingredientName, 0));
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    boolean getIngredient(String ingredient, int requestedVolume) {
        if (!ingredientInventoryMap.containsKey(ingredient)) {
            throw new RuntimeException("Ingredient " + ingredient + " Not Found In Inventory");
        }
        int curVolume = ingredientInventoryMap.getOrDefault(ingredient, 0);
        if (curVolume < requestedVolume) {
            sendAlert(ingredient);
            throw new RuntimeException("Insufficient amount of ingredient!! : " + ingredient);
        }
        curVolume = (curVolume - requestedVolume);
        ingredientInventoryMap.put(ingredient, curVolume);
        if (curVolume < 10) {
            sendAlert(ingredient);
        }
        return true;
    }

    private void sendAlert(String ingredient) {
        log.info("You are running low on this ingredient {}", ingredient);
        log.info("Do you want to add ingredient {}. If yes then press Y", ingredient);
        char permission = scanner.next().charAt(0);
        if (permission == 'Y') {
            log.info("Enter Quantity:");
            int t = scanner.nextInt();
            addIngredient(ingredient, t);
        }
    }
}