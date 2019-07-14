package com.nico.pagantis.model;

public class IncorrectBankTransfer extends RuntimeException {

    public IncorrectBankTransfer(String errorMessage) {
        super(errorMessage);
    }

}
