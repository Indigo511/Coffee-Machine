package com.example.coffeemachine.common.model;

import lombok.Data;

@Data
public class CoffeeFactory {

    public static Drink getCoffee(Drink coffee) {
        coffee.setName(coffee.getName().toUpperCase());
        switch (coffee.getName()) {
            case "CAPPUCCINO":
                return new Cappuccino(coffee.getName(), coffee.getRecipe(), coffee.getPrice(), coffee.getMixer());
            case "ESPRESSO":
                return new Espresso(coffee.getName(), coffee.getRecipe(), coffee.getPrice(), coffee.getMixer());
            case "AMERICANO":
                return new Americano(coffee.getName(), coffee.getRecipe(), coffee.getPrice(), coffee.getMixer());
            default:
                return new Cappuccino(coffee.getName(), coffee.getRecipe(), coffee.getPrice(), coffee.getMixer());
        }
    }
}
