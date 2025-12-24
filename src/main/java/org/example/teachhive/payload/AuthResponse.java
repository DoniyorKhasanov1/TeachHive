package org.example.teachhive.payload;

public record AuthResponse(
        boolean success,
        String message,
        String token
) {
}
