package co.inventorsoft.academy.musicmanager.dto.user;

import co.inventorsoft.academy.musicmanager.entity.Role;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        Role role
) {
}
