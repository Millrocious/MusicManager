package co.inventorsoft.academy.musicmanager.controller;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;
import co.inventorsoft.academy.musicmanager.service.SongService;
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
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongResponseDto addSong(@RequestBody @Valid SongRequestDto requestDto) {
        return songService.save(requestDto);
    }

    @GetMapping("/{id}")
    public SongResponseDto getSong(@PathVariable Long id) {
        return songService.findById(id);
    }

    @GetMapping
    public List<SongResponseDto> getAllSongs() {
        return songService.findAll();
    }

    @PutMapping("/{id}")
    public SongResponseDto updateSong(
            @PathVariable Long id,
            @RequestBody @Valid SongRequestDto requestDto
    ) {
        return songService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeSong(@PathVariable Long id) {
        songService.remove(id);
    }

    @GetMapping("/search")
    public  List<SongResponseDto> searchSongs(@RequestParam(required = false) String keyword) {
        return songService.searchSongs(keyword);
    }
}
