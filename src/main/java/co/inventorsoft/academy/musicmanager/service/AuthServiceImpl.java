package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.auth.AuthRequest;
import co.inventorsoft.academy.musicmanager.dto.auth.AuthResponse;
import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.entity.Role;
import co.inventorsoft.academy.musicmanager.entity.User;
import co.inventorsoft.academy.musicmanager.mapper.UserMapper;
import co.inventorsoft.academy.musicmanager.repository.UserRepository;
import co.inventorsoft.academy.musicmanager.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserRequestDto userDto) {
        if(userService.checkIfUserExist(userDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = userMapper.toEntity(userDto);

        user.setRole(Role.USER);
        userService.encodePassword(user, userDto);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(
                Map.of(
                        "user_id", user.getId(),
                        "role", user.getRole()
                ), user);

        return new AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = (User) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        ).getPrincipal();

        String jwtToken = jwtService.generateToken(
                Map.of(
                        "user_id", user.getId(),
                        "role", user.getRole()
                ), user);

        return new AuthResponse(jwtToken);
    }

}
