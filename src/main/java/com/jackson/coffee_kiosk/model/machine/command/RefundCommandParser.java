package com.jackson.coffee_kiosk.model.machine.command;

public class RefundCommandParser implements CommandParser {

    private static final String KEY_REFUND = "q";

    @Override
    public Command parse(String input) {
        if (KEY_REFUND.equalsIgnoreCase(input.trim())) {
            return new Command(Command.TYPE_REFUND, null);
        }
        return null;
    }
}
