package com.jackson.coffee_kiosk.view;

import com.jackson.coffee_kiosk.model.entity.MenuItem;

import java.util.List;
import java.util.Map;

public class CoffeeMachineView {
    private String formatMoney(int cents) {
        return String.format("$%.2f", cents / 100.0);
    }

    public void printStandbyScreen(String message) {
        System.out.println("Welcome to the Coffee Kiosk!");
        System.out.println("Please enter 'order' to begin ordering");
        System.out.println("(Hint: Inserting a coin will automatically wake up the machine)");

        System.out.println(" [ DISPLAY SCREEN ]");

        if (message != null && !message.isEmpty()) {
            System.out.println(" >> " + message);
        }
        System.out.print("> ");
    }
    public void printDashboard(List<MenuItem> menuList, int balanceInCents, String message, Map<String, Boolean> stockStatus) {

        System.out.println("***Coffee Kiosk***");
        System.out.println("==========================================");

        if (menuList == null || menuList.isEmpty()) {
            System.out.println("Menu data not available");
        } else {
            for (int i = 0; i < menuList.size(); i++) {
                MenuItem item = menuList.get(i);
                boolean isAvailable = stockStatus.getOrDefault(item.getName(), false);
                System.out.println((i + 1) + ". " + item.getName()  + formatMoney(item.getPrice()) + (isAvailable ? "" : " [Sold Out]"));
            }
        }

        System.out.println("==========================================");
        System.out.println("COMMANDS:");
        System.out.println(" - Insert Coin  : 'c' + amount (e.g., c100)");
        System.out.println(" - Buy Product  : ID (e.g., 1, 2)");
        System.out.println(" - q : return coins");
        System.out.println("------------------------------------------");
        System.out.println(" [ DISPLAY SCREEN ]");
        if (message != null && !message.isEmpty()) {
            System.out.println(" >> " + message);
        }
        System.out.println("Current Balance: " + formatMoney(balanceInCents));
        System.out.println("------------------------------------------");
        System.out.print("> ");
    }

    public void printSuccessScreen(String message) {
        System.out.println("\n\n\n\n");
        System.out.println("------------------------------------------");
        System.out.println("           ★ ORDER COMPLETED ★            ");
        if (message != null && !message.isEmpty()) {
            System.out.println("\n " + message + "\n");
        }
        System.out.println("       Press Enter to continue...         ");
        System.out.println("------------------------------------------");
        System.out.print("> ");
    }


}
