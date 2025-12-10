package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

public interface State {

    State STANDBY = new StandbyState();
    State ORDERING = new OrderingState();
    State MAKING = new MakingState();

    /**
     * Triggered when switching states
     */
    void onEnter(CoffeeMachine machine);


    State handleInput(CoffeeMachine machine, Command cmd);

    void updateView(CoffeeMachine machine, CoffeeMachineView view);
}
