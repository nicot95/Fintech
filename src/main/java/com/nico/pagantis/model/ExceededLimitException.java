package com.nico.pagantis.model;

public class ExceededLimitException extends RuntimeException {

    public ExceededLimitException(String errorMessage) {
        super(errorMessage);
    }
}
