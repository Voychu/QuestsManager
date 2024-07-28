package pl.aibron.quests.quest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.aibron.quests.user.User;

import java.util.List;

@RestController
public class QuestController {

    @Autowired
    private QuestService questService;

    @PostMapping("/quests")
    public Quest postQuest(@RequestBody Quest quest, @AuthenticationPrincipal User user){
        quest.setUser(user);
        return questService.saveDetails(quest);
    }
    @GetMapping("")
    public String home(){
        return "Home";
    }

    @GetMapping("/quests")
    public List<Quest> getAllQuests(@AuthenticationPrincipal User user){

        int user_id = user.getId();
        return questService.getAllDetails(user_id);
    }

    @GetMapping("/quests/{id}")
    public Quest getQuestById(@PathVariable int id, @AuthenticationPrincipal User user){

        int user_id = user.getId();
        return questService.getOneDetail(user_id,id);
    }

    @PutMapping("/quests/{id}")
    public Quest updateQuestById(@PathVariable int id, @RequestBody Quest quest, @AuthenticationPrincipal User user){

        int user_id = user.getId();
        return questService.updateDetail(id, quest, user_id);
    }

    @DeleteMapping("/quests/{id}")
    public void deleteQuestById(@PathVariable int id,  @AuthenticationPrincipal User user){

        int user_id = user.getId();
        questService.deleteOneDetail(user_id, id);
    }
}
