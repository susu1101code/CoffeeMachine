package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

public class StandbyState extends AbstractState {


    @Override
    public void updateView(CoffeeMachine machine, CoffeeMachineView view) {
        view.printStandbyScreen(machine.consumeMessage());
    }

    @Override
    protected State afterCoinInserted() {
        return State.ORDERING;
    }

    @Override
    public State handleInput(CoffeeMachine machine, Command cmd) {
        //check coin insertion
        State result = super.handleInput(machine, cmd);
        if (result != null) {
            return result;
        }
        // when user input order
        if (Command.TYPE_START_ORDER.equals(cmd.getCmd())) {
            return machine.ORDERING;
        }

        machine.setMessage("Machine is standby. Please type 'order' or insert coin.");
        return this;

    }

}
