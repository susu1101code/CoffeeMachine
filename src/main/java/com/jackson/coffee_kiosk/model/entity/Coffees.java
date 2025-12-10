package com.jackson.coffee_kiosk.model.entity;

import com.jackson.coffee_kiosk.model.factory.CappuccinoCoffeeFactory;
import com.jackson.coffee_kiosk.model.factory.CoffeeFactory;
import com.jackson.coffee_kiosk.model.factory.DecafCoffeeFactory;
import com.jackson.coffee_kiosk.model.factory.LatteCoffeeFactory;

import java.util.HashMap;
import java.util.Map;

public class Coffees {

    public static final String LATTE = "latte";
    public static final String CAPPUCCINO = "cappuccino";
    public static final String DECAF = "decaf";

    public static final Map<String, CoffeeFactory> FACTORY_MAP = new HashMap<>();

    static {
        FACTORY_MAP.put(LATTE, new LatteCoffeeFactory());
        FACTORY_MAP.put(CAPPUCCINO, new CappuccinoCoffeeFactory());
        FACTORY_MAP.put(DECAF, new DecafCoffeeFactory());

    }

    public static CoffeeFactory getFactory(String type) {
        return FACTORY_MAP.get(type.toLowerCase());
    }
}
