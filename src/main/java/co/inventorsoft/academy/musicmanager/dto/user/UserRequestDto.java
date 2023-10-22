package co.inventorsoft.academy.musicmanager.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank(message = "User name cannot be empty")
        String name,

        @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Password cannot be empty")
        String password
) {

}
