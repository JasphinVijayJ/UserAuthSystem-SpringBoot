package com.uas.exception;

public class OTPVerificationRequiredException extends RuntimeException {

    public OTPVerificationRequiredException(String message) {
        super(message);
    }
}