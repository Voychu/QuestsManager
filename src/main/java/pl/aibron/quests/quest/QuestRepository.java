package pl.aibron.quests.quest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest, Integer> {

    @Query("SELECT q FROM Quest q WHERE q.user.id = :userId")
    List<Quest> findAllQuestsByUserId(@Param("userId") int userId);

    @Query("SELECT q FROM Quest q WHERE q.user.id = :userId AND q.id = :id")
    Optional<Quest> findQuestByUserId(@Param("userId") int userId, @Param("id") int id);
}
