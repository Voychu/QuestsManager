package pl.aibron.quests.project;

import jakarta.persistence.*;
import lombok.Data;
import pl.aibron.quests.user.User;

@Entity
@Table(name = "PROJECTS")
@Data
public class Project {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID", nullable = false)
    private User user;


}
