package pl.aibron.quests.audio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.aibron.quests.quest.Quest;

import java.util.List;
import java.util.Optional;

public interface AudioRepository extends JpaRepository<Audio, Integer> {

    @Query("SELECT a FROM Audio a WHERE a.user.id = :userId")
    List<Audio> findAllAudiosByUserId(@Param("userId") int userId);

    @Query("SELECT a FROM Audio a WHERE a.user.id = :userId AND a.id = :id")
    Optional<Audio> findAudioByUserId(@Param("userId") int userId, @Param("id") int id);
}
