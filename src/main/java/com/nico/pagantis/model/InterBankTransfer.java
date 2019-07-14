package com.nico.pagantis.model;

import java.util.Random;

public class InterBankTransfer extends Transfer {

    private Random r;
    private static final Money commission = new Money(Money.Currency.EURO, 5);
    private static final Money limit = new Money(Money.Currency.EURO, 1000);
    private static final float failRate = 0.3f;

    public InterBankTransfer(Account from, Account to, Money money, Random random) {
        super(from, to, money);
        this.r = random;

        validateInterBankTransfer();
    }

    private void validateInterBankTransfer() {
        Bank fromBank = from.getCustomer().getBank();
        Bank toBank = to.getCustomer().getBank();

        if (money.isGreaterThan(limit)) {
            throw new ExceededLimitException("Money transferred is over the allowed limit of 1000E for Inter Bank Transfers");
        }

        if (fromBank.equals(toBank)) {
            throw new IncorrectBankTransfer("Accounts must be from different banks in an Inter Bank Transfer");
        }
    }

    @Override
    public synchronized boolean execute() {
        if (r.nextFloat() <= failRate) {
            return false;
        }

        from.subtractMoney(money);
        from.subtractMoney(commission);

        to.addMoney(money);

        return true;
    }
}
