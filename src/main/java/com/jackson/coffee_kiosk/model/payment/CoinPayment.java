package com.jackson.coffee_kiosk.model.payment;

import com.jackson.coffee_kiosk.model.entity.Coin;

public class CoinPayment implements Payment {
    private int balanceInCents = 0;

    @Override
    public void insertCoin(Coin coin) {
        if (coin != null) {
            balanceInCents += coin.getValueInCents();
        }
    }

    @Override
    public int getBalanceInCents() {
        return this.balanceInCents;
    }

    @Override
    public boolean deduct(int amountInCents) {
        if (balanceInCents >= amountInCents) {
            balanceInCents -= amountInCents;
            return true;
        }
        return false;
    }

    @Override
    public int refund() {
        int amountToRefund = balanceInCents;
        balanceInCents = 0;
        return amountToRefund;
    }
}
