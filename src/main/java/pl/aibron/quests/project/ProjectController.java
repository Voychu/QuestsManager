package pl.aibron.quests.project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.aibron.quests.user.User;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/projects")
    public Project postProject(@RequestBody Project project,  @AuthenticationPrincipal User user){
        project.setUser(user);
        return projectService.saveDetails(project);
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects(@AuthenticationPrincipal User user){
        int user_id = user.getId();
        return projectService.getAllDetails(user_id);
    }

    @GetMapping("/projects/{id}")
    public Project getProjectById(@PathVariable int id, @AuthenticationPrincipal User user){
        int user_id = user.getId();
        return projectService.getOneDetail(user_id,id);
    }

    @PutMapping("/projects/{id}")
    public Project updateProjectById(@PathVariable int id, @RequestBody Project project, @AuthenticationPrincipal User user){
        int user_id = user.getId();
        return projectService.updateDetail(id, project, user_id);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProjectById(@PathVariable int id, @AuthenticationPrincipal User user){
        int user_id = user.getId();
        projectService.deleteOneDetail(id, user_id);
    }
}
