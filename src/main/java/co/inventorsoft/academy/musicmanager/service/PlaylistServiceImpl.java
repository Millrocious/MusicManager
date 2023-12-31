package co.inventorsoft.academy.musicmanager.service;

import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistRequestDto;
import co.inventorsoft.academy.musicmanager.dto.playlist.PlaylistResponseDto;
import co.inventorsoft.academy.musicmanager.entity.Playlist;
import co.inventorsoft.academy.musicmanager.entity.Song;
import co.inventorsoft.academy.musicmanager.entity.User;
import co.inventorsoft.academy.musicmanager.mapper.PlaylistMapper;
import co.inventorsoft.academy.musicmanager.repository.PlaylistRepository;
import co.inventorsoft.academy.musicmanager.repository.SongRepository;
import co.inventorsoft.academy.musicmanager.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final PlaylistMapper playlistMapper;


    public PlaylistResponseDto save(Long userId, PlaylistRequestDto playlistRequestDto) {
        Playlist newPlaylist = playlistMapper.toEntity(playlistRequestDto);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        newPlaylist.setOwner(user);
        return playlistMapper.toResponseDto(playlistRepository.save(newPlaylist));
    }

    public List<PlaylistResponseDto> findAll() {
        return playlistRepository.findAll()
                .stream()
                .map(playlistMapper::toResponseDto)
                .toList();
    }

    public PlaylistResponseDto findById(Long id) {
        Playlist playlist = getExistingPlaylistById(id);
        return playlistMapper.toResponseDto(playlist);
    }

    public PlaylistResponseDto update(Long id, PlaylistRequestDto playlistRequestDto) {
        Playlist playlist = getExistingPlaylistById(id);
        playlistMapper.updateEntity(playlistRequestDto, playlist);
        playlistRepository.save(playlist);
        return playlistMapper.toResponseDto(playlist);
    }

    public void remove(Long id) {
        playlistRepository.deleteById(id);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = getExistingPlaylistById(playlistId);

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found"));

        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    public List<PlaylistResponseDto> searchPlaylists(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return findAll();
        }

        return playlistRepository.findPlaylistsByTitleContaining(keyword)
                .stream()
                .map(playlistMapper::toResponseDto)
                .toList();
    }

    private Playlist getExistingPlaylistById(long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));
    }
}
