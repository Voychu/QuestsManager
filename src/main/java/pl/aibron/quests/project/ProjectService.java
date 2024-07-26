package pl.aibron.quests.project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;


    public Project saveDetails(Project project){
        return projectRepository.save(project);
    }

    public List<Project> getAllDetails(int userId){
        return projectRepository.findAllProjectsByUserId(userId);
    }

    public Project getOneDetail(int userId, int id) {
        Optional<Project> project = projectRepository.findProjectByUserId(userId,id);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new ProjectNotFoundException("Project not found with id " + id);
        }
    }

    public Project updateDetail(int id, Project project, int userId){
        Project existingProject = getOneDetail(userId, id);
        existingProject.setDescription(project.getDescription());
        return projectRepository.save(existingProject);
    }

    public void deleteOneDetail(int userId, int id){
        Optional<Project> project = projectRepository.findProjectByUserId(userId, id);
        if (project.isPresent()) {
            projectRepository.deleteById(id);
        } else {
            throw new ProjectNotFoundException("Project not found with id " + id);
        }
    }

}
