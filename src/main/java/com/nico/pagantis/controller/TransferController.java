package com.nico.pagantis.controller;


import com.nico.pagantis.model.Account;
import com.nico.pagantis.model.ExceededLimitException;
import com.nico.pagantis.model.IncorrectBankTransfer;
import com.nico.pagantis.model.Money;
import com.nico.pagantis.respository.AccountRepository;
import com.nico.pagantis.respository.DummyAccountRepository;
import com.nico.pagantis.service.TransferAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TransferController {

    @Autowired
    private TransferAgentService transferAgentService;
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(path = "accounts", method = RequestMethod.GET)
    public ResponseEntity<Collection<Account>> getAccounts() {
        Collection<Account> accounts = accountRepository.getAll();

        return ResponseEntity.ok(accounts);
    }

    @RequestMapping(path = "/transfer/from/{fromId}/to/{toId}/amount/{amount}", method = RequestMethod.POST)
    public ResponseEntity<String> executeTransfer(@PathVariable("fromId") long from,
                                                   @PathVariable("toId") long to,
                                                   @PathVariable("amount") long amount) {

        Optional<Account> accountFrom = accountRepository.getAccount(from);
        Optional<Account>  accountTo = accountRepository.getAccount(to);

        if (!accountFrom.isPresent() || !accountTo.isPresent()) {
            long accountIdMissing = !accountFrom.isPresent() ? from : to;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with account id " + accountIdMissing + " not found.");
        }

        boolean successful;
        try {
            successful = transferAgentService.processTransfer(accountFrom.get(), accountTo.get(), new Money(Money.Currency.EURO, amount));
        } catch (ExceededLimitException | IncorrectBankTransfer ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(successful ? "Successful transfer" : "Failed transfer");
    }
}
