package com.jackson.coffee_kiosk.model.machine;

import com.jackson.coffee_kiosk.model.entity.Coffee;
import com.jackson.coffee_kiosk.model.entity.Coffees;
import com.jackson.coffee_kiosk.model.entity.Coin;
import com.jackson.coffee_kiosk.model.entity.MenuItem;
import com.jackson.coffee_kiosk.model.factory.CoffeeFactory;
import com.jackson.coffee_kiosk.model.machine.state.MakingState;
import com.jackson.coffee_kiosk.model.machine.state.OrderingState;
import com.jackson.coffee_kiosk.model.machine.state.StandbyState;
import com.jackson.coffee_kiosk.model.machine.state.State;
import com.jackson.coffee_kiosk.model.payment.Payment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    //dependencies
    private List<MenuItem> menu;
    private final Inventory inventory;
    private Payment paymentMethod;

    //caches
    private String displayMessage = "";
    private final Map<String, Boolean> stockCache = new HashMap<>();
    public Coffee pendingCoffee;

    //state
    public final State STANDBY = State.STANDBY;
    public final State ORDERING = State.ORDERING;
    public final State MAKING = State.MAKING;
    private State currentState;

    public CoffeeMachine(List<MenuItem> menu, Payment paymentMethod,Inventory inventory) {
        this.menu = menu;
        this.currentState = STANDBY;
        this.paymentMethod = paymentMethod;
        this.inventory = inventory;
        refreshStockCache();
    }

    /**
     * Check coffee's ingredient availability
     *
     * @param coffeeName
     * @return
     */
    public boolean isAvailable(String coffeeName) {
        return this.stockCache.getOrDefault(coffeeName, false);
    }

    public void consumeIngredients(Coffee recipe) {
        inventory.deduct(recipe.getRecipeMap());
        refreshStockCache();
    }

    /**
     * update the stock
     */
    private void refreshStockCache() {
        for (MenuItem item : menu) {
            CoffeeFactory factory = Coffees.getFactory(item.getName().toLowerCase());
            if (factory != null) {
                boolean enough = inventory.hasEnough(factory.getRecipe().getRecipeMap());
                stockCache.put(item.getName(), enough);
            }
        }
    }

    /**
     * read only stock cache
     * @return
     */
    public Map<String, Boolean> getStockStatus() {
        return Collections.unmodifiableMap(this.stockCache);
    }


    //state
    public void changeState(State newState) {
        if (newState != this.currentState) {
            newState.onEnter(this);
        }
        this.currentState = newState;
    }

    public State getCurrentState() {
        return currentState;
    }

    //payments
    public void insertCoin(Coin coin) {
        this.paymentMethod.insertCoin(coin);
    }

    public int getBalanceInCent() {
        return this.paymentMethod.getBalanceInCents();
    }

    public boolean deductAmount(int amount) {
        return this.paymentMethod.deduct(amount);
    }

    public int refund() {
        return this.paymentMethod.refund();
    }

    //display message
    public void setMessage(String msg) {
        this.displayMessage = msg;
    }

    // clear message after print
    public String consumeMessage() {
        String msg = this.displayMessage;
        this.displayMessage = "";
        return msg;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setPendingCoffee(Coffee coffee) {
        this.pendingCoffee = coffee;
    }

    public Coffee getPendingCoffee() {
        return pendingCoffee;
    }
}
