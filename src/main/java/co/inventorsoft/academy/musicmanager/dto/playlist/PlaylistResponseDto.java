package co.inventorsoft.academy.musicmanager.dto.song;

import co.inventorsoft.academy.musicmanager.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SongResponseDto(
        Long id,
        String title,
        String artist,
        Genre genre
) {
}
