package org.example.teachhive.mapper;

import lombok.RequiredArgsConstructor;
import org.example.teachhive.entity.User;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.RegisterRequest;
import org.example.teachhive.repository.UserRepository;
import org.example.teachhive.exception.RestException;
import org.example.teachhive.exception.ErrorCodes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest request) {
        if (request == null) throw new RestException("Null value not allowed", ErrorCodes.BadRequest);

        if (userRepository.existsByPhoneNumber(request.phoneNumber()))
            throw new RestException("User with this phone number already exists", ErrorCodes.AlreadyExists);

        return User.builder()
                .fullName(request.fullName())
                .username(request.username())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .deleted(false)
                .joinedAt(LocalDate.now(ZoneId.of("Asia/Tashkent")))
                .accountExpired(false)
                .build();
    }

    public User fromLoginRequest(LoginRequest request) {
        return userRepository.findByPhoneNumber(request.phoneNumber())
                .orElseThrow(() -> new RestException("Invalid credentials", ErrorCodes.InvalidParams));
    }
}
