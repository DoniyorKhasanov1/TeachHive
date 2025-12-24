package org.example.teachhive.service;

import org.example.teachhive.payload.AuthRequest;

public interface AuthService {
    void login(AuthRequest req);
}
