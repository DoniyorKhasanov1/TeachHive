package org.example.teachhive.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.payload.RegisterRequest;
import org.example.teachhive.service.AuthService;
import org.example.teachhive.util.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_PATH + "/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authentication Controller",
        description = "APIs for Authentication, exclusively for login"
)
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

}
