package co.inventorsoft.academy.musicmanager.controller;

import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistRequestDto;
import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistResponseDto;
import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.dto.user.UserResponseDto;
import co.inventorsoft.academy.musicmanager.service.PlaylistServiceImpl;
import co.inventorsoft.academy.musicmanager.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userService;
    private final PlaylistServiceImpl playlistService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserResponseDto> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) {
        return userService.update(id, userDto);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.remove(id);
    }

    @PostMapping("/{id}/playlists")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistResponseDto addPlaylist(@PathVariable Long id, @RequestBody @Valid PlaylistRequestDto requestDto) {
        return playlistService.save(id, requestDto);
    }
}