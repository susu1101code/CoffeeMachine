package com.jackson.coffee_kiosk.model.payment;


import com.jackson.coffee_kiosk.model.entity.Coin;

public interface Payment {
    void insertCoin(Coin coin);

    int getBalanceInCents();

    boolean deduct(int amountInCents);
    int refund();
}
