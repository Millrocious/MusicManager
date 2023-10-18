package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;

import java.util.List;

public interface SongService {
    SongResponseDto save(SongRequestDto requestDto);
    List<SongResponseDto> findAll();
    SongResponseDto findById(Long id);
    SongResponseDto update(Long id, SongRequestDto requestDto);
    void remove(Long id);
    List<SongResponseDto> searchSongs(String keyword);
}
