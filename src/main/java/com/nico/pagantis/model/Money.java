package com.nico.pagantis.model;

public class Money {

    public enum Currency {
        EURO, DOLLAR, POUND
    }

    private Currency currency;
    private long amount;

    public Money(Currency currency, long amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public long getAmount() {
        return amount;
    }

    public Money add(Money addedMoney) {
        if (this.currency != addedMoney.currency) {
            throw new IllegalArgumentException("Money added must be in the same currency");
        }

        return new Money(this.currency, this.amount + addedMoney.amount);
    }

    public Money subtract(Money addedMoney) {
        if (this.currency != addedMoney.currency) {
            throw new IllegalArgumentException("Money added must be in the same currency");
        }

        return new Money(this.currency, this.amount - addedMoney.amount);
    }

    public boolean isGreaterThan(Money money) {
        if (this.currency != money.currency) {
            throw new IllegalArgumentException("You cannot compare money of different currency");
        }

        return this.amount > money.amount;
    }
}
