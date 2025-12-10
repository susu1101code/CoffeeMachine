package com.jackson.coffee_kiosk.model.entity;

import com.jackson.coffee_kiosk.model.factory.CoffeeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeesTest {

    @Test
    @DisplayName("Test: Retrieve valid factory by name")
    void testGetFactory_Valid() {
        CoffeeFactory factory = Coffees.getFactory("Cappuccino");
        assertNotNull(factory, "Should find factory for Cappuccino");
        assertEquals("Cappuccino", factory.getRecipe().getName());
    }

    @Test
    @DisplayName("Test: Retrieve unknown factory returns null")
    void testGetFactory_Unknown() {
        CoffeeFactory factory = Coffees.getFactory("abc");
        assertNull(factory, "Should return null for unknown coffee name");
    }

    @Test
    @DisplayName("Test: Case insensitivity")
    void testGetFactory_CaseInsensitive() {
        CoffeeFactory factory = Coffees.getFactory("cappuccino");
         assertNotNull(factory);
    }
}