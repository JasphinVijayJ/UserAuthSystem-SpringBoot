package com.uas.service;

import com.uas.enums.ErrorMessage;
import com.uas.enums.SuccessMessage;
import com.uas.exception.*;
import com.uas.model.ForgotPassword;
import com.uas.model.User;
import com.uas.repository.ForgotPasswordRepository;
import com.uas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ForgotPasswordService {

    private final int OTP_EXPIRATION_MINUTES = 5;

    private final UserRepository userRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final EmailService emailService;

    public ForgotPasswordService(UserRepository userRepository,
                                 ForgotPasswordRepository forgotPasswordRepository,
                                 EmailService emailService) {
        this.userRepository = userRepository;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.emailService = emailService;
    }

    @Transactional
    public String sendOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email));

        // Delete any previous OTP for this user
        forgotPasswordRepository.deleteByEmail(email);

        // Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setEmail(email);
        forgotPassword.setOtp(otp);
        forgotPassword.setExpirationTime(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));

        forgotPasswordRepository.save(forgotPassword);


        emailService.sendEmail(email, otp, OTP_EXPIRATION_MINUTES);

        return SuccessMessage.OTP_SENT_SUCCESS.getMessage();
    }

    @Transactional
    public String verifyOtp(String email, String otp) {
        userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email));

        ForgotPassword forgotPassword = forgotPasswordRepository.findByEmailAndOtp(email, otp)
                .orElseThrow(() -> new InvalidOtpException(ErrorMessage.INVALID_OTP.getMessage()));

        if (forgotPassword.getExpirationTime().isBefore(LocalDateTime.now())) {
            forgotPasswordRepository.delete(forgotPassword);
            throw new OTPExpiredException(ErrorMessage.OTP_EXPIRED.getMessage());
        }

        if (forgotPassword.isVerified()) {
            return SuccessMessage.OTP_ALREADY_VERIFIED.getMessage();
        }

        forgotPassword.setVerified(true);
        forgotPasswordRepository.save(forgotPassword);
        return SuccessMessage.OTP_VERIFIED_SUCCESS.getMessage();
    }

    @Transactional
    public String resetPassword(String email, String newPassword, String confirmNewPassword) {
        if (!newPassword.equals(confirmNewPassword)) {
            throw new PasswordMismatchException(ErrorMessage.PASSWORD_MISMATCH.getMessage());
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage() + email));

        ForgotPassword forgotPassword = forgotPasswordRepository.findByEmail(email)
                .orElseThrow(() -> new OTPVerificationRequiredException(ErrorMessage.OTP_VERIFICATION_REQUIRED.getMessage()));

        if (!forgotPassword.isVerified()) {
            throw new OTPVerificationRequiredException(ErrorMessage.OTP_NOT_VERIFIED.getMessage());
        }

        if (forgotPassword.getExpirationTime().isBefore(LocalDateTime.now())) {
            forgotPasswordRepository.delete(forgotPassword);
            throw new OTPExpiredException(ErrorMessage.OTP_EXPIRED.getMessage());
        }

        user.setPassword(newPassword);
        userRepository.save(user);

        // Delete OTP after successful reset
        forgotPasswordRepository.delete(forgotPassword);

        return SuccessMessage.PASSWORD_RESET_SUCCESS.getMessage();
    }
}