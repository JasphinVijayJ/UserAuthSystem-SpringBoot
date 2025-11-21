package com.uas.enums;

public enum SuccessMessage {

    REGISTER_SUCCESS("Registration Successful! Please login."),
    LOGIN_SUCCESS("Login successful.");

    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}