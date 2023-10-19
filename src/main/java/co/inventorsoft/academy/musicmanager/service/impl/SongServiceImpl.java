package co.inventorsoft.academy.musicmanager.service.impl;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;
import co.inventorsoft.academy.musicmanager.entity.Song;
import co.inventorsoft.academy.musicmanager.mapper.SongMapper;
import co.inventorsoft.academy.musicmanager.repository.SongRepository;
import co.inventorsoft.academy.musicmanager.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Override
    public SongResponseDto save(SongRequestDto songRequestDto) {
        Song newSong = songMapper.toEntity(songRequestDto);
        return songMapper.toResponseDto(songRepository.save(newSong));
    }

    @Override
    public List<SongResponseDto> findAll() {
        return songRepository.findAll()
                .stream()
                .map(songMapper::toResponseDto)
                .toList();
    }

    @Override
    public SongResponseDto findById(Long id) {
        Song existingSong = getExistingSongById(id);
        return songMapper.toResponseDto(existingSong);
    }

    @Override
    public SongResponseDto update(Long id, SongRequestDto songRequestDto) {
        Song song = getExistingSongById(id);
        songMapper.updateEntity(songRequestDto, song);
        songRepository.save(song);
        return songMapper.toResponseDto(song);
    }

    @Override
    public void remove(Long id) {
        Song existingSong = getExistingSongById(id);
        songRepository.deleteById(id);
    }

    @Override
    public List<SongResponseDto> searchSongs(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return findAll();
        } else {
            return songRepository.findSongsByTitleContaining(keyword)
                    .stream()
                    .map(songMapper::toResponseDto)
                    .toList();
        }
    }

    private Song getExistingSongById(long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));
    }
}
