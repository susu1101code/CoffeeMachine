package com.jackson.coffee_kiosk.model.machine;

import com.jackson.coffee_kiosk.model.entity.Coin;
import com.jackson.coffee_kiosk.model.entity.Ingredient;
import com.jackson.coffee_kiosk.model.entity.MenuItem;
import com.jackson.coffee_kiosk.model.payment.Payment;

import java.util.HashMap;
import java.util.List;

public class CoffeeMachineMock{

    public CoffeeMachine machine;
    public Inventory inventory;
    public TestPayment payment;

    public static final String DEFAULT_COFFEE_NAME = "Cappuccino";
    public static final int DEFAULT_PRICE = 350;

    public CoffeeMachineMock() {
        inventory = new Inventory(new HashMap<>());
        resetInventory();
        payment = new TestPayment();
        List<MenuItem> menu = List.of(new MenuItem(DEFAULT_COFFEE_NAME, DEFAULT_PRICE));
        machine = new CoffeeMachine(menu, payment, inventory);
    }

    public void resetInventory() {
        inventory.addStock(Ingredient.WATER, 1000);
        inventory.addStock(Ingredient.MILK, 1000);
        inventory.addStock(Ingredient.STANDARD_BEAN, 100);
        inventory.addStock(Ingredient.DECAF_BEAN, 100);
    }

    public static class TestPayment implements Payment {
        public int balance = 0;

        @Override
        public void insertCoin(Coin coin) {
            balance += coin.getValueInCents();
        }

        @Override
        public int getBalanceInCents() {
            return balance;
        }

        @Override
        public boolean deduct(int amount) {
            balance -= amount;
            return balance > amount;
        }

        @Override
        public int refund() {
            int temp = balance;
            balance = 0;
            return temp;
        }
    }
}