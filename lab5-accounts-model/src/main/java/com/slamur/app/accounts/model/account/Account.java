package com.slamur.app.accounts.model.account;

import com.slamur.library.daolibrary.base.Item;

public class Account extends Item {

    private final String name;
    private double balance;
    private final String description;

    public Account(long id, String name, double balance, String description) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getDescription() {
        return description;
    }

    public void decreaseBalance(double delta) {
        this.balance -= delta;
    }

    public void increaseBalance(double delta) {
        this.balance += delta;
    }

    @Override
    public String toString() {
        return name;
    }
}
