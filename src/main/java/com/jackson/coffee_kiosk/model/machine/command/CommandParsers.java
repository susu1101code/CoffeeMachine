package com.jackson.coffee_kiosk.model.machine.command;

import java.util.ArrayList;
import java.util.List;

public class CommandParsers {

    private final List<CommandParser> commandParserList = new ArrayList<>();

    public void addCommandParser(CommandParser cp) {
        commandParserList.add(cp);
    }

    public Command parse(String input) {
        for (CommandParser cp : commandParserList) {
            Command cmd = cp.parse(input);
            if (cmd != null) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("Unknown command.");
    }
}
