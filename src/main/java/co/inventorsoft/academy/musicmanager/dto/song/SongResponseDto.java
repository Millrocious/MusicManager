package co.inventorsoft.academy.musicmanager.dto.song;

public record SongResponseDto(
        Long id,
        String title,
        String artist
) {
}
