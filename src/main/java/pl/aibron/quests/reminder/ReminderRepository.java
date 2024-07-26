package pl.aibron.quests.reminder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReminderRepository extends JpaRepository<Reminder, Integer> {

    @Query("SELECT r FROM Reminder r JOIN r.quest_id q JOIN q.user u WHERE u.id = :userId")
    List<Reminder> findAllRemindersByUserId(@Param("userId") int userId);

    @Query("SELECT r FROM Reminder r JOIN r.quest_id q JOIN q.user u WHERE u.id = :userId AND r.id=:id")
    Optional<Reminder> findReminderByUserId(@Param("userId") int userId, @Param("id") int id);

}
