package com.example.coffeemachine.common.model;

import java.util.Map;

public class Espresso extends Drink {

    String extraIngredient;

    public Espresso() {
        super(CoffeeTypes.ESPRESSO.name());
    }

    public Espresso(String drinkName, Recipe recipe, Double price, Mixer mixer) {
        super(CoffeeTypes.ESPRESSO.name(), recipe, price, mixer);
        this.extraIngredient = "added cream";
    }

    @Override
    public void process() {
        Map<String, Integer> ingredientMap = getWholeIngredient(recipe.getIngredientMap());
        mixer.makeDrink(recipe.getInstructionList(), ingredientMap);
    }
}