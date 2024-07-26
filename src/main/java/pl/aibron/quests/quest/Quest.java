package pl.aibron.quests.quest;


import jakarta.persistence.*;
import lombok.Data;
import pl.aibron.quests.project.Project;
import pl.aibron.quests.user.User;

import java.util.Date;

@Entity
@Table(name = "QUESTS")
@Data
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "QUEST_NAME")
    private String quest_name;
    @Column(name = "PRIORITY")
    private String priority;

    @Temporal(TemporalType.DATE)
    @Column(name = "DUE_DATE")
    private Date due_date;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID", nullable = true)
    private Project project_id;

    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID", nullable = false)
    private User user;

}
