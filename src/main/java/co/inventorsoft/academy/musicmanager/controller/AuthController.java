package co.inventorsoft.academy.musicmanager.controller;

import co.inventorsoft.academy.musicmanager.dto.auth.AuthRequest;
import co.inventorsoft.academy.musicmanager.dto.auth.AuthResponse;
import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    ResponseEntity<AuthResponse> auth(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRequestDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }
}