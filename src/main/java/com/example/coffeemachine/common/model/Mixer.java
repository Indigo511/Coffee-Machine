package com.example.coffeemachine.common.model;

import java.util.List;
import java.util.Map;

public class Mixer {

    private IngredientInventory ingredientInventory;

    public Mixer(IngredientInventory ingredientInventory) {
        this.ingredientInventory = ingredientInventory;
    }

    public void makeDrink(List<String> instructionList,
                          Map<String, Integer> ingredientMap) {
        ingredientMap.keySet().forEach(ingredient ->
                ingredientInventory.getIngredient(ingredient, ingredientMap.get(ingredient)));
    }
}