package com.uas.enums;

public enum SuccessMessage {

    REGISTER_SUCCESS("Registration completed successfully. Please login to continue."),
    LOGIN_SUCCESS("Login successful. Welcome to your account."),

    OTP_SENT_SUCCESS("Verification code has been sent to your email."),
    OTP_VERIFIED_SUCCESS("Email verification completed successfully."),
    OTP_ALREADY_VERIFIED("This code has already been used for verification."),
    PASSWORD_RESET_SUCCESS("Password has been reset successfully. You can now login.");

    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}