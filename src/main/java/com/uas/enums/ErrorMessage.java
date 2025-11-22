package com.uas.enums;

public enum ErrorMessage {

    REGISTER_FAILED("Registration failed. Please try again."),
    EMAIL_ALREADY_EXISTS("Email already registered. Use a different email."),
    PHONE_ALREADY_EXISTS("Phone number already in use. Try another number."),
    PASSWORD_MISMATCH("Passwords do not match. Please check and try again."),
    USER_NOT_FOUND("No account registered with this email: "),
    INVALID_PASSWORD("Incorrect password. Please try again."),

    INVALID_OTP("Invalid verification code. Please check and try again."),
    OTP_EXPIRED("Verification code expired. Request a new one."),
    OTP_VERIFICATION_REQUIRED("Email verification required before reset."),
    OTP_NOT_VERIFIED("Please complete email verification first.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    //	Getter Method
    public String getMessage() {
        return message;
    }
}