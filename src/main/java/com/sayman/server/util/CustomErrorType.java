package com.sayman.server.util;

public class CustomErrorType {
    private final String error;

    public CustomErrorType(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
