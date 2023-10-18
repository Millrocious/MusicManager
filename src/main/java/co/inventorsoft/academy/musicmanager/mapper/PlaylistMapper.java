package co.inventorsoft.academy.musicmanager.mapper;

import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistRequestDto;
import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistResponseDto;
import co.inventorsoft.academy.musicmanager.entity.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    PlaylistResponseDto toResponseDto(Playlist playlist);
    Playlist toEntity(PlaylistRequestDto playlistRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(PlaylistRequestDto playlistRequestDto, @MappingTarget Playlist playlist);
}
