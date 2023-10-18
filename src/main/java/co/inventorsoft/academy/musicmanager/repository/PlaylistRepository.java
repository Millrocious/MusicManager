package co.inventorsoft.academy.musicmanager.repository;

import co.inventorsoft.academy.musicmanager.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findPlaylistsByTitleContaining(String keyword);

}
