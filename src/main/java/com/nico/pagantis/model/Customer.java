package com.nico.pagantis.model;

public class Customer {

    private long id;
    private Bank bank;

    public Customer(long id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public long getId() {
        return id;
    }
}
