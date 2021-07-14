package com.example.coffeemachine.common.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class Recipe {
    private List<String> instructionList;
    private Map<String, Integer> ingredientMap = new LinkedHashMap<>();

    public void setInstructionList(List<String> instructionList) {
        this.instructionList = instructionList;
    }

    public void addIngredient(String ingredient, int ingredientAmount) {
        this.ingredientMap.put(ingredient, ingredientAmount);
    }
}
