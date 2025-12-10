package com.jackson.coffee_kiosk.model.machine;

import com.jackson.coffee_kiosk.model.entity.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Ingredient, Integer> stock = new HashMap<>();

    //default stock
    private static final Map<Ingredient, Integer> DEFAULT_STOCK = Map.of(
            Ingredient.WATER, 600,
            Ingredient.MILK, 550,
            Ingredient.STANDARD_BEAN, 40,
            Ingredient.DECAF_BEAN, 40
    );

    public Inventory() {
        this(DEFAULT_STOCK);
    }

    public Inventory(Map<Ingredient, Integer> initialStock) {
        if (initialStock != null) {
            initialStock.forEach(this::addStock);
        }
    }

    /**
     * refill
     *
     * @param ingredient
     * @param amount
     */
    public void addStock(Ingredient ingredient, int amount) {
        stock.put(ingredient, stock.getOrDefault(ingredient, 0) + amount);
    }

    public int getStock(Ingredient ingredient) {
        return stock.getOrDefault(ingredient, 0);
    }

    public boolean hasEnough(Map<Ingredient, Integer> recipeMap) {
        for (Map.Entry<Ingredient, Integer> entry : recipeMap.entrySet()) {
            Ingredient type = entry.getKey();
            int needed = entry.getValue();

            if (getStock(type) < needed) {
                return false;
            }
        }
        return true;
    }

    public void deduct(Map<Ingredient, Integer> recipeMap) {
        if (!hasEnough(recipeMap)) {
            throw new IllegalArgumentException("Not enough stock to deduct");
        } else {
            for (Map.Entry<Ingredient, Integer> entry : recipeMap.entrySet()) {
                Ingredient type = entry.getKey();
                int needed = entry.getValue();
                int current = getStock(type);
                stock.put(type, current - needed);
            }
        }
    }



}
