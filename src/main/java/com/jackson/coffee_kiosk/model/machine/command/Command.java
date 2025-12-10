package com.jackson.coffee_kiosk.model.machine.command;

public class Command {

    public static final String TYPE_INSERT_COIN = "INSERT_COIN";
    public static final String TYPE_SELECT_COFFEE = "SELECT_COFFEE";
    public static final String TYPE_REFUND = "REFUND";
    public static final String TYPE_START_ORDER = "START_ORDER";
    public static final String TYPE_PASS = "PASS";

    //cmd type
    private final String cmd;
    //data
    private final String args;

    public Command(String cmd, String args) {
        this.cmd = cmd;
        this.args = args;
    }

    public String getCmd() {
        return cmd;
    }

    public String getArgs() {
        return args;
    }
}