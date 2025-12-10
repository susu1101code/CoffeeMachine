package com.jackson.coffee_kiosk.model.factory;

import com.jackson.coffee_kiosk.model.entity.Coffee;

public interface CoffeeFactory {
    Coffee getRecipe();
    //hook of possibly future physical actions
    Coffee makeCoffee();
}
