package com.jackson.coffee_kiosk.controller;

import com.jackson.coffee_kiosk.model.machine.CoffeeMachine;
import com.jackson.coffee_kiosk.model.machine.command.*;
import com.jackson.coffee_kiosk.model.machine.state.State;
import com.jackson.coffee_kiosk.view.CoffeeMachineView;

import java.util.Scanner;

public class CoffeeController {
    private final CoffeeMachine machine;
    private final CoffeeMachineView view;
    private final Scanner scanner;
    private boolean running;
    private final CommandParsers commandParsers = new CommandParsers();

    public CoffeeController(CoffeeMachine machine, CoffeeMachineView view) {
        this.machine = machine;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.running = true;
        this.initCommandParser();
    }

    private void initCommandParser() {
        this.commandParsers.addCommandParser(new PassCommandParser());
        this.commandParsers.addCommandParser(new RefundCommandParser());
        this.commandParsers.addCommandParser(new StartOrderCommandParser());
        this.commandParsers.addCommandParser(new InsertCoinCommandParser());
        this.commandParsers.addCommandParser(new SelectCoffeeCommandParser());
    }

    public void run() {
        while (running) {
            //print standby state view
            machine.getCurrentState().updateView(machine, view);
            String input = scanner.nextLine();
            try {
                Command cmd = commandParsers.parse(input);
                State nextState = machine.getCurrentState().handleInput(machine, cmd);
                if (nextState != null) {
                    machine.changeState(nextState);
                }

            } catch (IllegalArgumentException e) {
                machine.setMessage(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                machine.setMessage("System Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

}
