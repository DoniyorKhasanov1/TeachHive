package org.example.teachhive.service;

import jakarta.mail.MessagingException;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.payload.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest req);

    AuthResponse register(RegisterRequest request) throws MessagingException;

    AuthResponse verifyEmail(String verificationCode);

    AuthResponse ignoreVerification(String token);
}
