package org.example.teachhive.payload;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        boolean success,
        String message
) {
}
