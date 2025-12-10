package com.jackson.coffee_kiosk.model.machine.command;

import com.jackson.coffee_kiosk.model.entity.Coin;

public class InsertCoinCommandParser implements CommandParser {

    private static final String REGEX_INSERT_COIN = "c\\d+";

    @Override
    public Command parse(String input) {
        String cmd = input.trim().toLowerCase();
        if (cmd.matches(REGEX_INSERT_COIN)) {
            // get amount
            String amount = cmd.substring(1);
            this.validate(amount);
            return new Command(Command.TYPE_INSERT_COIN, amount);
        }
        return null;
    }

    private void validate(String amount) throws IllegalAccessError {
        if (amount == null || amount.trim().isEmpty()) {
            throw new IllegalArgumentException("Coin value cannot be empty.");
        }

        int val;
        try {
            // has to be an integer
            val = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Coin value must be an integer.");
        }

        if (Coin.parse(val) == null) {
            throw new IllegalArgumentException("Unsupported coin.");
        }
    }
}
