package com.jackson.coffee_kiosk.model.machine.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParsersTest {

    private CommandParsers commandParsers;

    @BeforeEach
    void setUp() {
        commandParsers = new CommandParsers();
        commandParsers.addCommandParser(new InsertCoinCommandParser());
        commandParsers.addCommandParser(new SelectCoffeeCommandParser());
        commandParsers.addCommandParser(new RefundCommandParser());
        commandParsers.addCommandParser(new StartOrderCommandParser());
        commandParsers.addCommandParser(new PassCommandParser());
    }

    @Test
    @DisplayName("test insert coin")
    void testParse_InsertCoin() {

        Command result = commandParsers.parse("c50");

        assertNotNull(result);
        assertEquals("INSERT_COIN", result.getCmd());
        assertEquals("50", result.getArgs());
    }

    @Test
    @DisplayName("test select coffee")
    void testParse_SelectCoffee() {
        Command result = commandParsers.parse("1");

        assertNotNull(result);
        assertEquals("SELECT_COFFEE", result.getCmd());
        assertEquals("1", result.getArgs());
    }
    @Test
    @DisplayName("test refund")
    void testParse_Refund() {
        Command result = commandParsers.parse("q");

        assertNotNull(result);
        assertEquals("REFUND", result.getCmd());
        assertNull(result.getArgs());
    }

    @Test
    @DisplayName("test start order")
    void testParse_StartOrder() {
        Command result = commandParsers.parse("order");

        assertNotNull(result);
        assertEquals("START_ORDER", result.getCmd());
        assertNull(result.getArgs());
    }

    @Test
    @DisplayName("test input enter")
    void testParse_Pass() {
        Command result = commandParsers.parse("");

        assertNotNull(result);
        assertEquals("PASS", result.getCmd());
    }

    @Test
    @DisplayName("test unknow input")
    void testParse_Unknown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            commandParsers.parse("hello");
        });

        assertEquals("Unknown command.", exception.getMessage());
    }

    @Test
    @DisplayName("test unsupported coin")
    void testParse_InvalidCoin() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            commandParsers.parse("c1");
        });

        assertEquals("Unsupported coin.", exception.getMessage());
    }

    @Test
    @DisplayName("test wrong index of coffee")
    void testParse_InvalidSelection() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            commandParsers.parse("0");
        });

        assertEquals("Selection must be a positive number.", exception.getMessage());
    }
}