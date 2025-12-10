package com.jackson.coffee_kiosk.model.entity;

public class MenuItem {
    private String name;
    private int price;

    public MenuItem() {}
    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPrice() { return price; }
    public void setPrice(double priceInDollars) {
        this.price = (int) Math.round(priceInDollars * 100);
    }}
