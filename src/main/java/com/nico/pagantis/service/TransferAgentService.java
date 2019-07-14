package com.nico.pagantis.service;


import com.nico.pagantis.model.*;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TransferAgentService {

    private Random random;

    public TransferAgentService(Random random) {
        this.random = random;
    }

    public boolean processTransfer(Account from, Account to, Money money) {
        Bank fromBank = from.getCustomer().getBank();
        Bank toBank = to.getCustomer().getBank();

        Transfer transfer;
        if (fromBank.equals(toBank)) {
            transfer = new IntraBankTransfer(from, to, money);
        } else {
            transfer = new InterBankTransfer(from, to, money, random);
        }

        return transfer.execute();
    }
}
