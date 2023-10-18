package co.inventorsoft.academy.musicmanager.dto.playlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlaylistRequestDto(
        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotNull(message = "Description cannot be null")
        String description
) {
}
