package co.inventorsoft.academy.musicmanager.controller;

import co.inventorsoft.academy.musicmanager.dto.auth.AuthRequest;
import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.entity.Role;
import co.inventorsoft.academy.musicmanager.entity.User;
import co.inventorsoft.academy.musicmanager.mapper.UserMapper;
import co.inventorsoft.academy.musicmanager.repository.UserRepository;
import co.inventorsoft.academy.musicmanager.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    private static final String AUTHENTICATE_URL = "/api/auth/authenticate";
    private static final String REGISTER_URL = "/api/auth/register";

    @BeforeEach
    public void setup() {
        UserRequestDto userDto = new UserRequestDto("Test", "test@email.com", "password");

        User user = userMapper.toEntity(userDto);

        user.setRole(Role.USER);
        userService.encodePassword(user, userDto);
        userRepository.save(user);
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    void testAuthenticate_IncorrectPassword() throws Exception {
        AuthRequest authenticationRequest = new AuthRequest("test@email.com", "incorrect_password");

        mockMvc.perform(post(AUTHENTICATE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testAuthenticate_UserNotFound() throws Exception {
        AuthRequest authenticationRequest = new AuthRequest("nonexistent_user@email.com", "password");

        mockMvc.perform(post(AUTHENTICATE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRegister_UserAlreadyExists() throws Exception {
        UserRequestDto existingUserDto = new UserRequestDto("Test", "test@email.com", "password");

        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingUserDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void testRegister_InvalidEmailFormat() throws Exception {
        UserRequestDto invalidEmailUserDto = new UserRequestDto("InvalidUser", "invalid_email", "password");

        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEmailUserDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAuthenticate_ValidRequest() throws Exception {
        AuthRequest authenticationRequest = new AuthRequest("test@email.com", "password");

        mockMvc.perform(post(AUTHENTICATE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

    }

    @Test
    void testRegister_ValidRequest() throws Exception {
        UserRequestDto registerRequest = new UserRequestDto("User", "test_user@email.com", "password");

        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();
    }

}