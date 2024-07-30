package pl.aibron.quests.audio;

import jakarta.persistence.*;
import lombok.Data;
import pl.aibron.quests.user.User;

@Entity
@Table(name="AUDIOS")
@Data
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "AUDIO_DATA", columnDefinition="TEXT")
    private String audioData;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;
}
