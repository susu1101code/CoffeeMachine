package com.jackson.coffee_kiosk.model.machine.command;

public class SelectCoffeeCommandParser implements CommandParser {

    private static final String REGEX_SELECTION = "\\d+";

    @Override
    public Command parse(String input) {
        String cmd = input.trim().toLowerCase();
        if (cmd.matches(REGEX_SELECTION)) {
            this.validate(cmd);
            return new Command(Command.TYPE_SELECT_COFFEE, cmd);
        }
        return null;
    }

    private void validate(String args) {
        // null check
        if (args == null || args.trim().isEmpty()) {
            throw new IllegalArgumentException("Selection cannot be empty.");
        }

        try {
            //Selection has to be an Integer
            int index = Integer.parseInt(args);
            if (index <= 0) {
                throw new IllegalArgumentException("Selection must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid selection format.");
        }
    }
}
