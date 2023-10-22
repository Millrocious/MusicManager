package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto update(Long id, UserRequestDto userRequestDto);
    void remove(Long id);

}
