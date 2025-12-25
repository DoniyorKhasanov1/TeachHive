package org.example.teachhive.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.teachhive.entity.User;
import org.example.teachhive.exception.ErrorCodes;
import org.example.teachhive.exception.RestException;
import org.example.teachhive.mapper.UserMapper;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.repository.UserRepository;
import org.example.teachhive.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    /**
     * Mapper for mapping User entities to response and/or vice versa
     */
    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public AuthResponse login(User user) {
        if (Objects.isNull(user))
            throw new RestException("User must not be null", ErrorCodes.InvalidParams);
        this.repository.findByPhoneNumber()
    }

    @Override
    public AuthResponse register(User user) {
        return null;
    }
}
