package co.inventorsoft.academy.musicmanager.service.impl;

import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.dto.user.UserResponseDto;
import co.inventorsoft.academy.musicmanager.entity.User;
import co.inventorsoft.academy.musicmanager.mapper.UserMapper;
import co.inventorsoft.academy.musicmanager.repository.UserRepository;
import co.inventorsoft.academy.musicmanager.service.UserService;
import co.inventorsoft.academy.musicmanager.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtil userUtil;

    @Override
    public UserResponseDto findById(Long id) {
        User user = userUtil.getExistingUser(id);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userDto) {
        User user = userUtil.getExistingUser(id);
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .toList();
    }


}
