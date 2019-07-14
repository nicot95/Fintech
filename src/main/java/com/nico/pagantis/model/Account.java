package com.nico.pagantis.model;

import java.util.List;

public class Account {

    private long id;
    private Customer customer;
    private Money money;
    private List<Transfer> transfers;

    public Account(long id, Customer customer, Money money, List<Transfer> transfers) {
        this.id = id;
        this.customer = customer;
        this.money = money;
        this.transfers = transfers;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Money getMoney() {
        return money;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void addMoney(Money addedMoney) {
        this.money = money.add(addedMoney);
    }

    public void subtractMoney(Money addedMoney) {
        this.money = money.subtract(addedMoney);
    }
}
