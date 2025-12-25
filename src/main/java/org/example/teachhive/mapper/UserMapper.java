package org.example.teachhive.mapper;

import org.example.teachhive.entity.User;
import org.example.teachhive.enums.Role;
import org.example.teachhive.payload.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(LoginRequest request) {
        if (request != null) {
            return new User(
                    request.fullName(),
                    request.username(),
                    request.phoneNumber(),
                    request.password(),
                    Role.ROLE_USER,
                    false,
                    false,
                    true
            );
        }
        return new User();
    }
}
