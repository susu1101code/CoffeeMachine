package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.entity.Coffee;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

public class MakingState extends AbstractState {

    //making coffee
    @Override
    public void onEnter(CoffeeMachine machine) {
        Coffee coffee = machine.getPendingCoffee();
        String name = (coffee != null) ? coffee.getName() : "Coffee";
        machine.setMessage("Here is your " + name + ". Enjoy!");
        machine.setPendingCoffee(null);
    }

    @Override
    public void updateView(CoffeeMachine machine, CoffeeMachineView view) {
        view.printSuccessScreen(machine.consumeMessage());
    }

    @Override
    public State handleInput(CoffeeMachine machine, Command cmd) {
        return machine.getBalanceInCent() > 0 ? machine.ORDERING : machine.STANDBY;

    }


}
