package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistRequestDto;
import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistResponseDto;

import java.util.List;

public interface PlaylistService {
    PlaylistResponseDto save(PlaylistRequestDto requestDto);
    List<PlaylistResponseDto> findAll();
    PlaylistResponseDto findById(Long id);
    PlaylistResponseDto update(Long id, PlaylistRequestDto requestDto);
    void remove(Long id);
    void addSongToPlaylist(Long playlistId, Long songId);
}
