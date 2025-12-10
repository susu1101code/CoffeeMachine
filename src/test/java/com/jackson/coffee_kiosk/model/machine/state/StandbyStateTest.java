package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachineMock;
import com.jackson.coffee_kiosk.model.machine.Inventory;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.model.payment.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StandbyStateTest {

    private CoffeeMachine machine;
    private StandbyState state;

    @BeforeEach
    void setUp() {
        CoffeeMachineMock mock = new CoffeeMachineMock();
        this.machine = mock.machine;
        state = new StandbyState();
    }

    @Test
    @DisplayName("Test: 'order' command triggers transition to ORDERING")
    void testHandleInput_StartOrder() {
        Command cmd = new Command(Command.TYPE_START_ORDER, null);
        State nextState = state.handleInput(machine, cmd);
        assertTrue(nextState instanceof OrderingState, "Should transition to OrderingState");
    }

    @Test
    @DisplayName("Test: Inserting coin triggers transition to ORDERING")
    void testHandleInput_InsertCoin() {

        Command cmd = new Command(Command.TYPE_INSERT_COIN, "50");
        State nextState = state.handleInput(machine, cmd);
        assertTrue(nextState instanceof OrderingState, "Should transition to OrderingState");
        assertTrue(machine.consumeMessage().contains("Received $0.50"));
    }

    @Test
    @DisplayName("Test: Other commands are ignored in STANDBY")
    void testHandleInput_Ignored() {
        Command cmd = new Command(Command.TYPE_SELECT_COFFEE, "1");
        State nextState = state.handleInput(machine, cmd);
        assertTrue(nextState instanceof StandbyState, "Should stay in STANDBY");
        assertEquals("Machine is standby. Please type 'order' or insert coin.", machine.consumeMessage());
    }
}