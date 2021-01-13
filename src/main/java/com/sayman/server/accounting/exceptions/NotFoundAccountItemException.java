package com.sayman.server.accounting.exceptions;

public class NotFoundAccountItemException extends AccountingException {
    public NotFoundAccountItemException(String message) {
        super(message);
    }
}
