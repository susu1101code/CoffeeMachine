package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.entity.Coffee;
import com.jackson.coffee_kiosk.model.entity.Coffees;
import com.jackson.coffee_kiosk.model.entity.Coin;
import com.jackson.coffee_kiosk.model.entity.MenuItem;
import com.jackson.coffee_kiosk.model.factory.CoffeeFactory;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

import java.util.List;
import java.util.Map;

public class OrderingState extends AbstractState {
    @Override
    public void updateView(CoffeeMachine machine, CoffeeMachineView view) {
        Map<String, Boolean> stockStatus = machine.getStockStatus();
        view.printDashboard(machine.getMenu(), machine.getBalanceInCent(), machine.consumeMessage(), stockStatus);
    }

    @Override
    public State handleInput(CoffeeMachine machine, Command cmd) {
        State result = super.handleInput(machine, cmd);
        if (result != null) {
            return result;
        }

        if (Command.TYPE_REFUND.equals(cmd.getCmd())) {
            int refunded = machine.refund();
            machine.setMessage("Return " + Coin.formatMoney(refunded) + " back. ");
            return machine.STANDBY;
        }

        if (Command.TYPE_SELECT_COFFEE.equals(cmd.getCmd())) {
            return processSelection(machine, cmd.getArgs());
        }
        return this;

    }

    private State processSelection(CoffeeMachine machine, String args) {
        int index = Integer.parseInt(args);
        List<MenuItem> menu = machine.getMenu();
        if (index > menu.size()) {
            machine.setMessage("Invalid selection number!");
            return this;
        }
        //The first item index of the menu is 1
        MenuItem item = menu.get(index - 1);
        String coffeeName = item.getName();

        // Failed purchases because out of stock, stay in ORDERING state
        if (!machine.isAvailable(coffeeName)) {
            machine.setMessage("Sorry the selected is out of stock.");
            return this;
        }
        // Failed purchases because out of balance, stay in ORDERING state
        if (machine.getBalanceInCent() < item.getPrice()) {
            machine.setMessage("Insufficient balance!");
            return this;
        }

        // Successful purchase
        CoffeeFactory factory = Coffees.getFactory(coffeeName);
        if (factory == null) {
            machine.setMessage("Recipe not found.");
            return this;
        }

        Coffee recipe = factory.getRecipe();
        machine.consumeIngredients(recipe);
        machine.deductAmount(item.getPrice());
        machine.setPendingCoffee(factory.makeCoffee());
        return machine.MAKING;
    }

}
