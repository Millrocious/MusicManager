package co.inventorsoft.academy.musicmanager.mapper;

import co.inventorsoft.academy.musicmanager.dto.user.UserRequestDto;
import co.inventorsoft.academy.musicmanager.dto.user.UserResponseDto;
import co.inventorsoft.academy.musicmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);
    User toEntity(UserRequestDto userDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UserRequestDto userDto, @MappingTarget User user);
}
