package com.banana.banana_invoices.exceptions;

public class NullElementException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NullElementException(String errorMessage) {
        super(errorMessage);
    }
}
