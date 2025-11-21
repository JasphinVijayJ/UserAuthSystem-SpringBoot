package com.uas.enums;

public enum ErrorMessage {

    REGISTER_FAILED("Registration failed."),
    EMAIL_ALREADY_EXISTS("Email is already in use."),
    PHONE_ALREADY_EXISTS("Phone number is already in use."),
    PASSWORD_MISMATCH("Password and Confirm Password do not match."),
    USER_NOT_FOUND("No account registered with this email: "),
    INVALID_PASSWORD("Incorrect password.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    //	Getter Method
    public String getMessage() {
        return message;
    }
}