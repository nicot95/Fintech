package com.nico.pagantis.model;

public abstract class Transfer {

    protected Account from;
    protected Account to;
    protected Money money;

    public Transfer(Account from, Account to, Money money) {
        this.from = from;
        this.to = to;
        this.money = money;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public abstract boolean execute();
}
