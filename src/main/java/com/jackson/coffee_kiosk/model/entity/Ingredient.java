package com.jackson.coffee_kiosk.model.entity;

public enum Ingredient {
    WATER("ml"),
    MILK("ml"),
    STANDARD_BEAN("g"),
    DECAF_BEAN("g");

    private final String unit;
    Ingredient(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

}
