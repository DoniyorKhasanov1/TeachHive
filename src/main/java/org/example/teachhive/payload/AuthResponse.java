package org.example.teachhive.payload;

import lombok.Builder;

@Builder
public record AuthResponse(
        boolean success,
        String message,
        String token
) {
}
