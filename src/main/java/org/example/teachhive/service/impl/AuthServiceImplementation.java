package org.example.teachhive.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.config.security.JwtService;
import org.example.teachhive.entity.User;
import org.example.teachhive.mapper.UserMapper;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.payload.RegisterRequest;
import org.example.teachhive.repository.UserRepository;
import org.example.teachhive.service.AuthService;
import org.example.teachhive.service.EmailVerificationService;
import org.example.teachhive.util.UserRegisteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailVerificationService emailVerificationService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) throws MessagingException {
        User user = userMapper.toEntity(request);
        user.setIsEnabled(false);
        User savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserRegisteredEvent(savedUser.getId()));
        String token = jwtService.generateToken(user.getPhoneNumber());
        log.info("User {} successfully registered\nEmail sent to {}", user.getFullName(), user.getEmail());
        return AuthResponse.builder()
                .token(token)
                .success(true)
                .message("REGISTRATION SUCCESSFUL")
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userMapper.fromLoginRequest(request);
        String token = jwtService.generateToken(user.getPhoneNumber());
        return AuthResponse.builder()
                .token(token)
                .success(true)
                .message("LOGIN SUCCESSFUL")
                .build();
    }

    @Override
    @Transactional
    public AuthResponse verifyEmail(String token) {
        emailVerificationService.verifyEmail(token);
        return AuthResponse.builder()
                .success(true)
                .message("Email verified successfully")
                .build();
    }

    @Override
    @Transactional
    public AuthResponse ignoreVerification(String token) {
        emailVerificationService.ignoreVerification(token);
        return AuthResponse.builder()
                .success(true)
                .message("Verification ignored")
                .build();
    }
}
