package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.dto.user.UserResponseDto;
import co.inventorsoft.academy.musicmanager.entity.User;
import co.inventorsoft.academy.musicmanager.mapper.UserMapper;
import co.inventorsoft.academy.musicmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto findById(Long id) {
        User user = getExistingUser(id);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    public UserResponseDto update(Long id, UserRequestDto userDto) {
        User user = getExistingUser(id);
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .toList();
    }

    public void encodePassword(User userEntity, UserRequestDto user){
        userEntity.setPassword(passwordEncoder.encode(user.password()));
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getExistingUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
