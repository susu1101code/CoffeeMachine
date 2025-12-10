package com.jackson.coffee_kiosk;

import com.jackson.coffee_kiosk.controller.CoffeeController;
import com.jackson.coffee_kiosk.model.entity.MenuItem;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.Inventory;
import com.jackson.coffee_kiosk.model.payment.CoinPayment;
import com.jackson.coffee_kiosk.model.payment.Payment;
import com.jackson.coffee_kiosk.model.repository.MenuLoader;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        MenuLoader loader = new MenuLoader();
        List<MenuItem> menu = loader.load("menu.json");
        if (menu.isEmpty()) {
            System.err.println("Failed to load menu. System shutting down.");
            return;
        }
        Payment payment = new CoinPayment();
        Inventory inventory = new Inventory();
        CoffeeMachine machine = new CoffeeMachine(menu, payment,inventory);
        CoffeeMachineView view = new CoffeeMachineView();
        CoffeeController controller = new CoffeeController(machine, view);
        controller.run();

    }
}
