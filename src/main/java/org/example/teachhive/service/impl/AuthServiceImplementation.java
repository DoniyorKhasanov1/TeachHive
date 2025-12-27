package org.example.teachhive.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.config.security.JwtService;
import org.example.teachhive.entity.User;
import org.example.teachhive.exception.ErrorCodes;
import org.example.teachhive.exception.RestException;
import org.example.teachhive.mapper.UserMapper;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.payload.RegisterRequest;
import org.example.teachhive.repository.UserRepository;
import org.example.teachhive.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        User user = userMapper.toEntity(request);
        userRepository.save(user);
        String token = jwtService.generateToken(user.getPhoneNumber());
        log.info("User {} successfully registered", user.getFullName());
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

}
