package co.inventorsoft.academy.musicmanager.repository;

import co.inventorsoft.academy.musicmanager.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findSongsByTitleContaining(String keyword);
}
