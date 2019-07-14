package com.nico.pagantis.model;

public class IntraBankTransfer extends Transfer {

    private final static Money commision = new Money(Money.Currency.EURO, 0);

    public IntraBankTransfer(Account from, Account to, Money money) {
        super(from, to, money);

        validateIntraBankTransfer();
    }

    private void validateIntraBankTransfer() {
        Bank fromBank = from.getCustomer().getBank();
        Bank toBank = to.getCustomer().getBank();

        if (!fromBank.equals(toBank)) {
            throw new IncorrectBankTransfer("Accounts must be from the same bank in an Intra Bank Transfer");
        }
    }

    @Override
    public synchronized boolean execute() {
        from.subtractMoney(money);
        to.addMoney(money);

        return true;
    }
}
