package com.example.coffeemachine.common.model;

import lombok.Data;

@Data
public class Ingredient {
    private String name;

    public Ingredient(final String name) {
        this.name = name;
    }
}
