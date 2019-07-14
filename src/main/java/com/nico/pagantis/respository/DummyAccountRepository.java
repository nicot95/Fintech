package com.nico.pagantis.respository;

import com.nico.pagantis.model.Account;
import com.nico.pagantis.model.Bank;
import com.nico.pagantis.model.Customer;
import com.nico.pagantis.model.Money;

import java.util.*;

public class DummyAccountRepository implements AccountRepository {

    private Map<Long, Account> accountMap;

    public DummyAccountRepository() {
        accountMap = new HashMap<>();

        Bank santander = new Bank(1, "Santander");
        Bank bbva = new Bank(2, "BBVA");

        Customer santanderCustomer1 = new Customer(1, santander);
        Customer santanderCustomer2 = new Customer(2, santander);
        Customer bbvaCustomer = new Customer(1, bbva);

        Account account1 = new Account(1, santanderCustomer1, new Money(Money.Currency.EURO, 10000), new ArrayList<>());
        Account account2 = new Account(2, santanderCustomer2, new Money(Money.Currency.EURO, 5000), new ArrayList<>());
        Account account3 = new Account(3, bbvaCustomer, new Money(Money.Currency.EURO, 3000), new ArrayList<>());

        accountMap.put(1L, account1);
        accountMap.put(2L, account2);
        accountMap.put(3L, account3);
    }

    public Optional<Account> getAccount(long id) {
        return Optional.ofNullable(accountMap.get(id));
    }

    public Collection<Account> getAll() {
        return accountMap.values();
    }
}
