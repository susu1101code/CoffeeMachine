package com.jackson.coffee_kiosk.model.machine.command;

public class StartOrderCommandParser implements CommandParser {

    private static final String KEY_START_ORDER = "order";

    @Override
    public Command parse(String input) {
        if (KEY_START_ORDER.equalsIgnoreCase(input.trim())) {
            return new Command(Command.TYPE_START_ORDER, null);
        }
        return null;
    }
}
