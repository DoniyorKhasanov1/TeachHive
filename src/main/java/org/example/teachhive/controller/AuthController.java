package org.example.teachhive.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.teachhive.payload.LoginRequest;
import org.example.teachhive.payload.AuthResponse;
import org.example.teachhive.payload.RegisterRequest;
import org.example.teachhive.service.AuthService;
import org.example.teachhive.util.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.BASE_PATH + "/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authentication Controller",
        description = "APIs for Authentication, exclusively for login"
)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) throws MessagingException {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/verify")
    @Operation(description = "Verifying code sent to email")
    public ResponseEntity<AuthResponse> verifyEmail(@RequestParam("token") String token) {
        return ResponseEntity.ok(authService.verifyEmail(token));
    }

    @GetMapping("/ignore")
    @Operation(description = "Ignoring when user presses 'Ignore verification button in message sent to email'")
    public ResponseEntity<AuthResponse> ignoreVerification(@RequestParam("token") String token) {
        return ResponseEntity.ok(authService.ignoreVerification(token));
    }

}
