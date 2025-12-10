package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.entity.Coin;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

public abstract class AbstractState implements State {

    /**
     * All states can handle coin insertion and carriage return inputs
     * @param machine
     * @param cmd
     * @return
     */
    @Override
    public State handleInput(CoffeeMachine machine, Command cmd) {
        if(Command.TYPE_PASS.equals(cmd.getCmd())) {
            return this;
        }

        // handle coin insertion
        if (Command.TYPE_INSERT_COIN.equals(cmd.getCmd())) {
            return processCoinInsert(machine, cmd);
        }

        return null;
    }

    protected State processCoinInsert(CoffeeMachine machine, Command cmd) {
        int val = Integer.parseInt(cmd.getArgs());
        Coin coin = Coin.parse(val);
        if (coin == null) {
            machine.setMessage("Illegal coin denominations");
            return this;
        }
        machine.insertCoin(coin);

        machine.setMessage("Received " + Coin.formatMoney(coin.getValueInCents()));

        //call hook to change state
        return afterCoinInserted();
    }

    // default: stay in the same state
    protected State afterCoinInserted() {
        return this;
    }

    @Override
    public void onEnter(CoffeeMachine machine) {}

    @Override
    public abstract void updateView(CoffeeMachine machine, CoffeeMachineView view);

}
