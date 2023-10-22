package co.inventorsoft.academy.musicmanager.dto.auth;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
