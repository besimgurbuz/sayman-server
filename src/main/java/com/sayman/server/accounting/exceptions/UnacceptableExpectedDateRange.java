package com.sayman.server.accounting.exceptions;

public class UnacceptableExpectedDateRange extends RuntimeException {
    public UnacceptableExpectedDateRange(String message) {
        super(message);
    }
}
