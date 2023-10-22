package co.inventorsoft.academy.musicmanager.util;

import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.entity.User;
import co.inventorsoft.academy.musicmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public User getExistingUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}