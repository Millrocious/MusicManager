package co.inventorsoft.academy.musicmanager.controller;

import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistRequestDto;
import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistResponseDto;
import co.inventorsoft.academy.musicmanager.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping("/{id}")
    public PlaylistResponseDto getPlaylist(@PathVariable Long id) {
        return playlistService.findById(id);
    }

    @GetMapping
    public List<PlaylistResponseDto> getAllPlaylists() {
        return playlistService.findAll();
    }

    @PutMapping("/{id}")
    public PlaylistResponseDto updatePlaylist(
            @PathVariable Long id,
            @RequestBody @Valid PlaylistRequestDto requestDto
    ) {
        return playlistService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePlaylist(@PathVariable Long id) {
        playlistService.remove(id);
    }

    @PostMapping("/{playlistId}/{songId}")
    public String addSongToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId
    ) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return "Song added to the playlist successfully";
    }

    @GetMapping("/search")
    public List<PlaylistResponseDto> searchPlaylists(@RequestParam(required = false) String keyword) {
        return playlistService.searchPlaylists(keyword);
    }
}
