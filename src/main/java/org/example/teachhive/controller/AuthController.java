package org.example.teachhive.controller;

import lombok.RequiredArgsConstructor;
import org.example.teachhive.payload.AuthRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private ResponseEntity<String> login(AuthRequest request){ //Vaqtinchalik String qaytaramiz
        service.login(request);
        return ResponseEntity.ok("SUCCESS");
    }
}
