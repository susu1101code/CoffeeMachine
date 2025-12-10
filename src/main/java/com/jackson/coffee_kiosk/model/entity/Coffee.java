package com.jackson.coffee_kiosk.model.entity;

import java.util.Map;

public class Coffee {

    private final String name;


    private final Map<Ingredient, Integer> recipeMap;

    public Coffee(String name, Map<Ingredient, Integer> recipeMap) {
        this.name = name;
        this.recipeMap = recipeMap;
    }

    public String getName() {
        return name;
    }
    public Map<Ingredient, Integer> getRecipeMap() {
        return  recipeMap;
    }

}
