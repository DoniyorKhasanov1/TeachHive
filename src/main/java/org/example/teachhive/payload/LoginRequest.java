package org.example.teachhive.payload;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password must be specified so you can re-login with that")
        String password
) {
}
