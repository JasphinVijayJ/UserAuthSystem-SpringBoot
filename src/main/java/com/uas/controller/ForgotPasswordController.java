package com.uas.controller;

import com.uas.dto.ForgotPasswordRequest;
import com.uas.dto.ResetPasswordRequest;
import com.uas.dto.VerifyOtpRequest;
import com.uas.service.ForgotPasswordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uas/user/password")
@CrossOrigin(origins = "http://localhost:5173")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(forgotPasswordService.sendOtp(request.getEmail()));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
        return ResponseEntity.ok(forgotPasswordService.verifyOtp(request.getEmail(), request.getOtp()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(forgotPasswordService.resetPassword(
                request.getEmail(),
                request.getNewPassword(),
                request.getConfirmNewPassword()
        ));
    }
}