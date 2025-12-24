package org.example.teachhive.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.teachhive.config.security.JwtService;
import org.example.teachhive.entity.User;
import org.example.teachhive.exception.ErrorCodes;
import org.example.teachhive.exception.RestException;
import org.example.teachhive.payload.AuthRequest;
import org.example.teachhive.repository.UserRepository;
import org.example.teachhive.service.AuthService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final JwtService jwtService;
    private UserRepository repository;
    @Override
    public void login(AuthRequest request) {
        User user = repository.findByUsername(request.username())
                .orElseThrow(() -> new RestException("User Not Found", ErrorCodes.NotFound));
    }
}
