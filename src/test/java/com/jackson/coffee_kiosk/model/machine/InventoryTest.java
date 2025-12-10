package com.jackson.coffee_kiosk.model.machine;

import com.jackson.coffee_kiosk.model.entity.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(new HashMap<>());
    }

    @Test
    @DisplayName("test adding stock")
    void addStock() {
        inventory.addStock(Ingredient.WATER, 100);
        assertEquals(100, inventory.getStock(Ingredient.WATER));
    }

    @Test
    @DisplayName("test get stock returns correct or 0 if empty")
    void getStock() {
        inventory.addStock(Ingredient.WATER, 10);
        assertEquals(10, inventory.getStock(Ingredient.WATER));
        assertEquals(0, inventory.getStock(Ingredient.MILK));
    }
    @Test
    @DisplayName("test hasEnough - false")
    void hasEnough_False() {
        inventory.addStock(Ingredient.WATER, 10);
        Map<Ingredient, Integer> recipe = Map.of(Ingredient.WATER, 50);

        assertFalse(inventory.hasEnough(recipe));
    }

    @Test
    @DisplayName("test hasEnough - true")
    void hasEnough_True() {
        inventory.addStock(Ingredient.WATER, 10);
        Map<Ingredient, Integer> recipe = Map.of(Ingredient.WATER, 10);

        assertTrue(inventory.hasEnough(recipe));
    }



    @Test
    @DisplayName("test success deduct should maintain correct stock level")
    void deduct_Success() {
        inventory.addStock(Ingredient.WATER, 100);
        Map<Ingredient, Integer> recipe = Map.of(Ingredient.WATER, 30);
        inventory.deduct(recipe);

        assertEquals(70, inventory.getStock(Ingredient.WATER));
    }
    @DisplayName("test deduct throws exception when stock is insufficient")
    void deduct_Fail() {
        inventory.addStock(Ingredient.WATER, 100);

        Map<Ingredient, Integer> heavyRecipe = Map.of(Ingredient.WATER, 200);
        assertThrows(IllegalArgumentException.class, () -> {
            inventory.deduct(heavyRecipe);
        });
        assertEquals(100, inventory.getStock(Ingredient.WATER));
    }

}