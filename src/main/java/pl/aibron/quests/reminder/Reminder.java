package pl.aibron.quests.reminder;

import jakarta.persistence.*;
import lombok.Data;
import pl.aibron.quests.quest.Quest;

import java.time.LocalDateTime;

@Entity
@Table(name = "REMINDERS")
@Data
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "QUEST_ID", referencedColumnName = "ID", nullable = true)
    private Quest quest_id;

    private LocalDateTime reminderDateTime;

}
