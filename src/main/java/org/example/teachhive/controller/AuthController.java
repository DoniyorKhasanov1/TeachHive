package org.example.teachhive.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authentication Controller",
        description = "APIs for Authentication, exclusively for login"
)
public class AuthController {
    private final AuthService service;

    @PostMapping
    private ResponseEntity<AuthResponse> login(LoginRequest request){
        service.login(request);
        return ResponseEntity.ok("SUCCESS");
    }
}
