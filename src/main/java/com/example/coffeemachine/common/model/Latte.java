package com.example.coffeemachine.common.model;

import java.util.Map;

public class Latte extends Drink {

    public Latte() {
        super(CoffeeTypes.LATTE.name());
    }

    public Latte(String drinkName, Recipe recipe, Double price, Mixer mixer) {
        super(CoffeeTypes.LATTE.name(), recipe, price, mixer);
    }

    @Override
    public void process() {
        Map<String, Integer> ingredientMap = getWholeIngredient(recipe.getIngredientMap());
        mixer.makeDrink(recipe.getInstructionList(), ingredientMap);
    }

    @Override
    public void addCommonIngredient(String ingredient, int volume) {
        this.commonIngredient.put(ingredient, volume);
    }
}