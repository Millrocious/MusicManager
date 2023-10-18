package co.inventorsoft.academy.musicmanager.dto.song;

import co.inventorsoft.academy.musicmanager.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SongRequestDto(
        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotBlank(message = "Artist cannot be empty")
        String artist,
        @NotNull(message = "Duration cannot be empty")
        Integer duration,
        @NotNull(message = "Genre cannot be empty")
        Genre genre
) {
}
