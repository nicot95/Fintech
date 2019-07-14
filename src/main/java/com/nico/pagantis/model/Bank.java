package com.nico.pagantis.model;

import java.util.Objects;

public class Bank {

    private long bankId;
    private String bankName;

    public Bank(long bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    public long getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return bankId == bank.bankId &&
                Objects.equals(bankName, bank.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId, bankName);
    }
}

