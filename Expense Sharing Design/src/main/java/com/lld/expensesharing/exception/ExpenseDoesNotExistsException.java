package com.lld.expensesharing.exception;

public class ExpenseDoesNotExistsException extends Exception {

    public ExpenseDoesNotExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return this.getMessage();
    }
}