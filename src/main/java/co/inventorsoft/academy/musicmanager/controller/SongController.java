package co.inventorsoft.academy.musicmanager.controller;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;
import co.inventorsoft.academy.musicmanager.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongController {
    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResponseDto> addSong(
            @RequestBody SongRequestDto requestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(songService.save(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponseDto> getSong(@PathVariable Long id) {
        return ResponseEntity.ok(songService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SongResponseDto>> getAllTasks() {
        return ResponseEntity.ok(songService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongResponseDto> updateTask(@PathVariable Long id, @RequestBody SongRequestDto requestDto) {
        return ResponseEntity.accepted().body(songService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTask(@PathVariable Long id) {
        songService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
