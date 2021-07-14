package com.example.coffeemachine.common.model;

import java.util.Map;

public class Americano extends Drink {

    public Americano() {
        super(CoffeeTypes.AMERICANO.name());
    }

    public Americano(String drinkName, Recipe recipe, Double price, Mixer mixer) {
        super(CoffeeTypes.AMERICANO.name(), recipe, price, mixer);
    }

    @Override
    public void process() {
        Map<String, Integer> ingredientMap =
                getWholeIngredient(recipe.getIngredientMap());
        mixer.makeDrink(recipe.getInstructionList(), ingredientMap);
    }
}