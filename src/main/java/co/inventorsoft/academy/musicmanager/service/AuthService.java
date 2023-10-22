package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.auth.AuthRequest;
import co.inventorsoft.academy.musicmanager.dto.auth.AuthResponse;
import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;

public interface AuthService {
    AuthResponse register(UserRequestDto user);
    AuthResponse authenticate(AuthRequest user);
}
