package org.example.teachhive.payload;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Phone number is required")
        String phoneNumber,
        @NotBlank(message = "Password must be specified so you can re-login with that")
        String password
) {
}
