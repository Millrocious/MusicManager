package co.inventorsoft.academy.musicmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.inventorsoft.academy.musicmanager.dto.song.SongRequestDto;
import co.inventorsoft.academy.musicmanager.dto.song.SongResponseDto;
import co.inventorsoft.academy.musicmanager.entity.Song;
import co.inventorsoft.academy.musicmanager.mapper.SongMapper;
import co.inventorsoft.academy.musicmanager.repository.SongRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class SongServiceImplTest {
    AutoCloseable openMocks;

    @InjectMocks
    private SongServiceImpl songService;

    @Mock
    private SongRepository songRepository;

    @Mock
    private SongMapper songMapper;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testSaveSong() {
        SongRequestDto requestDto = new SongRequestDto("Song Title", "Artist Name", 240);
        Song newSong = new Song();
        SongResponseDto expectedResponse = new SongResponseDto(1L, "Song Title", "Artist Name");

        when(songMapper.toEntity(requestDto)).thenReturn(newSong);
        when(songRepository.save(newSong)).thenReturn(newSong);
        when(songMapper.toResponseDto(newSong)).thenReturn(expectedResponse);

        SongResponseDto response = songService.save(requestDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testFindAllSongs() {
        List<Song> songs = List.of(new Song(), new Song());
        List<SongResponseDto> expectedResponse = List.of(new SongResponseDto(1L, "Song 1", "Artist 1"), new SongResponseDto(2L, "Song 2", "Artist 2"));

        when(songRepository.findAll()).thenReturn(songs);
        when(songMapper.toResponseDto(any(Song.class))).thenReturn(expectedResponse.get(0), expectedResponse.get(1));

        List<SongResponseDto> response = songService.findAll();

        assertEquals(expectedResponse, response);
    }

    @Test
    void testFindSongById() {
        Long songId = 1L;
        Song song = new Song();
        SongResponseDto expectedResponse = new SongResponseDto(songId, "Song Title", "Artist Name");

        when(songRepository.findById(songId)).thenReturn(Optional.of(song));
        when(songMapper.toResponseDto(song)).thenReturn(expectedResponse);

        SongResponseDto response = songService.findById(songId);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testUpdateSong() {
        Long songId = 1L;
        SongRequestDto requestDto = new SongRequestDto("Updated Title", "Updated Artist", 300);
        Song existingSong = new Song();
        SongResponseDto expectedResponse = new SongResponseDto(songId, "Updated Title", "Updated Artist");

        when(songRepository.findById(songId)).thenReturn(Optional.of(existingSong));
        when(songRepository.save(existingSong)).thenReturn(existingSong);
        when(songMapper.toResponseDto(existingSong)).thenReturn(expectedResponse);

        SongResponseDto response = songService.update(songId, requestDto);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testRemoveSong() {
        Long songId = 1L;

        songService.remove(songId);

        verify(songRepository, times(1)).deleteById(songId);
    }

    @Test
    void testSearchSongsWithKeyword() {
        String keyword = "Song";
        List<Song> songs = List.of(new Song(), new Song());
        List<SongResponseDto> expectedResponse = List.of(new SongResponseDto(1L, "Song 1", "Artist 1"), new SongResponseDto(2L, "Song 2", "Artist 2"));

        when(songRepository.findSongsByTitleContaining(keyword)).thenReturn(songs);
        when(songMapper.toResponseDto(any(Song.class))).thenReturn(expectedResponse.get(0), expectedResponse.get(1));

        List<SongResponseDto> response = songService.searchSongs(keyword);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testSearchSongsWithEmptyKeyword() {
        String keyword = "";
        List<Song> songs = List.of(new Song(), new Song());
        List<SongResponseDto> expectedResponse = List.of(new SongResponseDto(1L, "Song 1", "Artist 1"), new SongResponseDto(2L, "Song 2", "Artist 2"));

        when(songRepository.findAll()).thenReturn(songs);
        when(songMapper.toResponseDto(any(Song.class))).thenReturn(expectedResponse.get(0), expectedResponse.get(1));

        List<SongResponseDto> response = songService.searchSongs(keyword);

        assertEquals(expectedResponse, response);
    }
}
