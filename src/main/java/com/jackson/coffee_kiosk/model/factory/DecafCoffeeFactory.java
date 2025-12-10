package com.jackson.coffee_kiosk.model.factory;

import com.jackson.coffee_kiosk.model.entity.Coffee;
import com.jackson.coffee_kiosk.model.entity.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class DecafCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee getRecipe() {
        Map<Ingredient, Integer> recipe = new HashMap<>();
        recipe.put(Ingredient.WATER, 100);
        recipe.put(Ingredient.MILK, 200);
        recipe.put(Ingredient.DECAF_BEAN, 20);
        return new Coffee("Decaf", recipe);
    }


    @Override
    public Coffee makeCoffee() {
        return getRecipe();
    }
}
