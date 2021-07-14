package com.example.coffeemachine.common.model;

import java.util.Map;

public class Cappuccino extends Drink {

    public Cappuccino() {
        super(CoffeeTypes.CAPPUCCINO.name());
    }

    public Cappuccino(String drinkName, Recipe recipe, Double price, Mixer mixer) {
        super(CoffeeTypes.valueOf(drinkName).name(), recipe, price, mixer);
    }

    @Override
    public void process() {
        Map<String, Integer> ingredientMap =
                getWholeIngredient(recipe.getIngredientMap());
        mixer.makeDrink(recipe.getInstructionList(), ingredientMap);
    }
}