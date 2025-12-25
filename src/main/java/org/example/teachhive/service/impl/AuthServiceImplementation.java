//package org.example.teachhive.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.teachhive.config.security.JwtService;
//import org.example.teachhive.entity.User;
//import org.example.teachhive.exception.ErrorCodes;
//import org.example.teachhive.exception.RestException;
//import org.example.teachhive.payload.LoginRequest;
//import org.example.teachhive.payload.AuthResponse;
//import org.example.teachhive.payload.RegisterRequest;
//import org.example.teachhive.repository.UserRepository;
//import org.example.teachhive.service.AuthService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class AuthServiceImplementation implements AuthService {
//
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    @Transactional(readOnly = true)
//    public AuthResponse login(LoginRequest request) {
//        User user = userRepository.findByPhoneNumber(request.phoneNumber())
//                .orElseThrow(() -> new RestException("Invalid Credentials", ErrorCodes.InvalidParams));
//
//        // Check if account is enabled and not deleted
//        if (!user.isEnabled()) {
//            throw new RestException("Account is disabled or deleted", ErrorCodes.Unauthorized);
//        }
//
//        // Verify password
//        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
//            throw new RestException("Invalid username or password", ErrorCodes.INVALID_CREDENTIALS);
//        }
//
//        // Generate JWT token
//        String token = jwtService.generateToken(user.getPassword());
//
//        return AuthResponse.builder()
//                .token(token)
//                .success(true)
//                .message("Login successful")
//                .build();
//    }
//
//
//    @Override
//    public AuthResponse register(RegisterRequest request) {
//        if (request == null) throw new RestException("Null value not permitted", ErrorCodes.BadRequest);
//        if (userRepository.existsByPhoneNumber(request.phoneNumber())){
//            throw new RestException("User with this phone number Already Exists", ErrorCodes.AlreadyExists);
//        }
//
//        User.builder()
//                .fullName(request.fullName())
//                .username(request.userName())
//                .phoneNumber(request.phoneNumber())
//                .password(passwordEncoder.encode(request.password()))
//                .email(request.email())
//                .build();
//
//
//    }
//}
