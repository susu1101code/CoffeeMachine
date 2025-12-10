package com.jackson.coffee_kiosk.model.entity;

/**
 * 1c/2c are illegal types of coins
 */
public enum Coin {
    FIVE_CENTS(5),
    TEN_CENTS(10),
    TWENTY_CENTS(20),
    FIFTY_CENTS(50),
    ONE_DOLLAR(100),
    TWO_DOLLARS(200);

    private final int valueInCents;

    Coin(final int valueInCents) {
        this.valueInCents = valueInCents;
    }

    public int getValueInCents() {
        return valueInCents;
    }

    public static String formatMoney(int cents) {
        return String.format("$%.2f", cents / 100.0);
    }

    public static Coin parse(final int cents) {
        for (Coin coin : values()) {
            if (coin.valueInCents == cents) {
                return coin;
            }
        }
        return null;
    }
}
