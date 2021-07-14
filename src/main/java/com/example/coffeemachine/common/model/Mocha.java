package com.example.coffeemachine.common.model;

import java.util.Map;

public class Mocha extends Drink {

    public Mocha() {
        super(CoffeeTypes.MOCHA.name());
    }

    public Mocha(String drinkName, Recipe recipe, Double price, Mixer mixer) {
        super(CoffeeTypes.MOCHA.name(), recipe, price, mixer);
    }

    @Override
    public void process() {
        Map<String, Integer> ingredientMap =
                getWholeIngredient(recipe.getIngredientMap());
        mixer.makeDrink(recipe.getInstructionList(), ingredientMap);
    }

    @Override
    public void addCommonIngredient(String ingredient, int volume) {
        this.commonIngredient.put(ingredient, volume);
    }
}