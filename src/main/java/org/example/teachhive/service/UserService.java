package org.example.teachhive.service;

import org.example.teachhive.entity.User;
import org.example.teachhive.payload.AuthResponse;

public interface UserService {
    AuthResponse login(User user);
    AuthResponse register(User user);
}
