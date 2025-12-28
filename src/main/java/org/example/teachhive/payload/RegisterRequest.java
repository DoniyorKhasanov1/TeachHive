package org.example.teachhive.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record RegisterRequest(

        @NotBlank(message = "Full name is required")
        @Size(min = 3)
        String fullName,

        @NotBlank(message = "Username must be provided")
        @Size(min = 3)
        String username,

        @NotBlank(message = "Phone Number must be provided")
        String phoneNumber,

        @NotBlank(message = "Email is required")
        @Email(message = "Provided email must be valid")
        @Size(min = 6, message = "Email must contain at least 6 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password
) {
}
