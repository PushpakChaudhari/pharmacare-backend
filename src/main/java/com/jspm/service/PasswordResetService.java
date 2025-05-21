package com.jspm.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.jspm.model.PasswordResetToken;
import com.jspm.model.User;
import com.jspm.repository.PasswordResetTokenRepository;
import com.jspm.repository.UserRepository;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void sendResetToken(String email) {
        System.out.println("Sending password reset token to: " + email);

        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String newTokenString = UUID.randomUUID().toString();
        LocalDateTime newExpiryDate = LocalDateTime.now().plusHours(1);

        // Try to find an existing token for the user
        Optional<PasswordResetToken> existingToken = tokenRepository.findByUser(user);

        if (existingToken.isPresent()) {
            PasswordResetToken token = existingToken.get();
            token.setToken(newTokenString);
            token.setExpiryDate(newExpiryDate);
            tokenRepository.save(token); // performs update
        } else {
            PasswordResetToken token = new PasswordResetToken();
            token.setToken(newTokenString);
            token.setExpiryDate(newExpiryDate);
            token.setUser(user);
            tokenRepository.save(token); // performs insert
        }

        // Send the re`set email
        emailService.sendResetEmail(email, newTokenString);
    }



}
