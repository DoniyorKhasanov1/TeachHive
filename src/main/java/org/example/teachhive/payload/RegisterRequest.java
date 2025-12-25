package org.example.teachhive.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record RegisterRequest(
        @NotBlank(message = "Full name is required")
        @Size(min = 3)
        String fullName,
        @NotBlank(message = "Username must be provided")
        @Size(min = 3)
        String userName,
        @NotBlank(message = "Phone Number must be provided")
        String phoneNumber,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
