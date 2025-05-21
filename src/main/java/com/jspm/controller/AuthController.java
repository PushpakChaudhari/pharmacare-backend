//package com.jspm.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import com.jspm.model.User;
//import com.jspm.service.UserService;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//    @CrossOrigin(origins = "http://localhost:5173")
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        // Check if the user exists and if the password is correct
//        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }
//    @CrossOrigin(origins = "http://localhost:5173")
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        if (userService.existsByUsername(user.getUsername())) {
//            return ResponseEntity.badRequest().body("Username is already taken");
//        }
//        User registeredUser = userService.save(user);
//        return ResponseEntity.ok(registeredUser);
//    }
//}


package com.jspm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jspm.model.ForgotPasswordRequest;
import com.jspm.model.PasswordResetToken;
import com.jspm.model.ResetPasswordRequest;
import com.jspm.model.User;
import com.jspm.repository.PasswordResetTokenRepository;
import com.jspm.repository.UserRepository;
import com.jspm.service.PasswordResetService;
import com.jspm.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import com.jspm.util.JwtUtil;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") 
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService resetService;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());

        if (isAuthenticated) {
            String token = JwtUtil.generateToken(user.getUsername());

            // Return token and username in JSON response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("username", user.getUsername());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        User registeredUser = userService.save(user);
        return ResponseEntity.ok(registeredUser);
    }
    

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        resetService.sendResetToken(request.getEmail());
        return ResponseEntity.ok("Reset email sent successfully");
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        // Validate token
        PasswordResetToken token = passwordResetTokenRepository.findByToken(request.getToken())
            .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token has expired");
        }

        // Update user's password
        User user = token.getUser();
        user.setPassword(request.getNewPassword()); // Consider encoding this
        userRepository.save(user);

        // Optionally: delete or invalidate token after use
        passwordResetTokenRepository.delete(token);

        return ResponseEntity.ok("Password reset successful");
    }

}

