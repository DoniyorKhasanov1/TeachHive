package org.example.teachhive.payload;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "Phone number is required")
        String phoneNumber,
        @NotBlank(message = "Email must be specified to verify with code")
        String email,
        @NotBlank(message = "Full name is required")
        String fullName,
        @NotBlank(message = "Username must be specified and unique")
        String username
) {
}
