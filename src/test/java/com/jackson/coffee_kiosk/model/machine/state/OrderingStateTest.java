package com.jackson.coffee_kiosk.model.machine.state;

import com.jackson.coffee_kiosk.model.entity.Ingredient;
import com.jackson.coffee_kiosk.model.entity.MenuItem;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.CoffeeMachineMock;
import com.jackson.coffee_kiosk.model.machine.Inventory;
import com.jackson.coffee_kiosk.model.machine.command.Command;
import com.jackson.coffee_kiosk.model.payment.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrderingStateTest {

    private CoffeeMachine machine;
    private OrderingState state;
    private Inventory inventory;
    private CoffeeMachineMock.TestPayment payment;

    private static final String COFFEE_NAME = "Cappuccino";
    private static final int PRICE = 350;

    @BeforeEach
    void setUp() {
        CoffeeMachineMock mock = new CoffeeMachineMock();
        inventory = mock.inventory;
        payment = mock.payment;
        machine = mock.machine;
        state = new OrderingState();
    }

    @Test
    @DisplayName("Test Refund: Should return money and switch to STANDBY")
    void testHandleInput_Refund() {
        payment.balance = 100;
        Command cmd = new Command(Command.TYPE_REFUND, null);

        State nextState = state.handleInput(machine, cmd);
        assertEquals(machine.STANDBY, nextState);
        assertEquals(0, payment.getBalanceInCents());
        assertTrue(machine.consumeMessage().contains("Return $1.00 back"));
    }

    @Test
    @DisplayName("Test Selection: Invalid Index should not change state")
    void testProcessSelection_InvalidIndex() {
        Command cmd = new Command(Command.TYPE_SELECT_COFFEE, "99");
        State nextState = state.handleInput(machine, cmd);
        assertEquals(state, nextState);
        assertEquals("Invalid selection number!", machine.consumeMessage());
    }

    @Test
    @DisplayName("Test Selection: Insufficient Balance")
    void testProcessSelection_NoMoney() {
        payment.balance = 100;
        Command cmd = new Command(Command.TYPE_SELECT_COFFEE, "1");
        State nextState = state.handleInput(machine, cmd);
        assertEquals(state, nextState);
        assertEquals("Insufficient balance!", machine.consumeMessage());
    }

    @Test
    @DisplayName("Test Selection: Out of Stock")
    void testProcessSelection_NoStock() {
        payment.balance = 500;
        // empty inventory
        inventory = new Inventory(new HashMap<>());
        machine = new CoffeeMachine(machine.getMenu(), payment, inventory);
        Command cmd = new Command(Command.TYPE_SELECT_COFFEE, "1");
        State nextState = state.handleInput(machine, cmd);
        assertEquals(state, nextState);
        assertEquals("Sorry the selected is out of stock.", machine.consumeMessage());
    }

    @Test
    @DisplayName("Test Selection Success: Deduct money/stock, switch to MAKING")
    void testProcessSelection_Success() {
        payment.balance = 500;
        Command cmd = new Command(Command.TYPE_SELECT_COFFEE, "1");
        State nextState = state.handleInput(machine, cmd);
        //switch to making
        assertEquals(machine.MAKING, nextState);

        //payment check
        assertEquals(150, payment.getBalanceInCents());

        // check get correct coffee
        assertNotNull(machine.getPendingCoffee());
        assertEquals(COFFEE_NAME, machine.getPendingCoffee().getName());
    }
}