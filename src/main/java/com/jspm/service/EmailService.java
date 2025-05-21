package com.jspm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetEmail(String toEmail, String token) {
        String subject = "🔐 Reset Your Pharmacare Password";
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;

        String body = """
                Hello,

                We received a request to reset your Pharmacare account password.
                Click the link below to reset it:

                👉 %s

                If you didn’t request this, you can safely ignore this email.
                Your password will remain unchanged.

                — 
                💊 Pharmacare Support Team
                --------------------------------------
                © 2025 Pharmacare. All rights reserved.
                """.formatted(resetUrl);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}