package com.example.coffeemachine.common.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Drink {

    protected String name;
    protected Recipe recipe;
    protected Double price;
    protected Mixer mixer;
    protected Map<String, Integer> commonIngredient;

    Drink(String name) {
        this.name = name;
    }

    Drink(String name, Recipe recipe, Double price, Mixer mixer) {
        this.name = name;
        this.recipe = recipe;
        this.price = price;
        this.mixer = mixer;
    }

    public void addCommonIngredient(String ingredient, int volume) {
        this.commonIngredient.put(ingredient, volume);
    }

    public Map<String, Integer> getWholeIngredient(Map<String, Integer> drinkSpecificIngredientMap) {
        Map<String, Integer> map = new LinkedHashMap<>(drinkSpecificIngredientMap);
        if (!CollectionUtils.isEmpty(commonIngredient)) {
            map.putAll(commonIngredient);
        }
        return map;
    }

    public void process() {
    }
}