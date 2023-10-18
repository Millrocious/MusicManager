package co.inventorsoft.academy.musicmanager.mapper;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;
import co.inventorsoft.academy.musicmanager.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongResponseDto toResponseDto(Song task);
    Song toEntity(SongRequestDto taskDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(SongRequestDto userDto, @MappingTarget Song task);
}
