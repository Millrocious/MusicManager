package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.SongResponseDto;

import java.util.List;

public interface SongService {
    SongResponseDto save(SongRequestDto task);
    List<SongResponseDto> findAll();
    SongResponseDto findById(Long id);
    SongResponseDto update(Long id, SongRequestDto task);
    void remove(Long id);
}
