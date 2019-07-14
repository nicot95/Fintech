package com.nico.pagantis.respository;

import com.nico.pagantis.model.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository {

    public Optional<Account> getAccount(long id);

    public Collection<Account> getAll();
}
