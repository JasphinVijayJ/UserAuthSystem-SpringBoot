package com.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String email, String otp, int expirationMinutes) {
        SimpleMailMessage message = new SimpleMailMessage();

        String subject = "Password Reset Request";

        String text = String.format(
                """
                        Hello,
                        
                        You have requested to reset your password.
                        Your verification code is: %s
                        
                        This code will expire in %d minutes.
                        
                        If you didn't request this, please ignore this email.
                        
                        Best regards,
                        Your App Team
                        """,
                otp, expirationMinutes
        );
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}