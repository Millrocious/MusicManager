package co.inventorsoft.academy.musicmanager.mapper;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;
import co.inventorsoft.academy.musicmanager.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongResponseDto toResponseDto(Song song);
    Song toEntity(SongRequestDto songRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(SongRequestDto songRequestDto, @MappingTarget Song song);
}
