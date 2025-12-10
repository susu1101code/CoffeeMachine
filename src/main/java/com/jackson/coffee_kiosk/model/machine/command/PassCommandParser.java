package com.jackson.coffee_kiosk.model.machine.command;

public class PassCommandParser implements CommandParser {

    private static final String KEY_REFUND = "q";

    @Override
    public Command parse(String input) {
        if (input == null || input.isEmpty()) {
            return new Command(Command.TYPE_PASS, null);
        }
        return null;
    }
}
