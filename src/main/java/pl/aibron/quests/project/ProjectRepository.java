package pl.aibron.quests.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT p FROM Project p WHERE p.user.id = :userId")
    List<Project> findAllProjectsByUserId(@Param("userId") int userId);

    @Query("SELECT p FROM Project p WHERE p.user.id = :userId AND p.id = :id")
    Optional<Project> findProjectByUserId(@Param("userId") int userId, @Param("id") int id);
}
