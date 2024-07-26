package pl.aibron.quests.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.aibron.quests.user.User;

import java.util.List;

@RestController
public class ReminderController {


    @Autowired
    private ReminderService reminderService;

    @PostMapping("/reminders")
    public Reminder postReminder(@RequestBody Reminder quest){
        return reminderService.saveDetails(quest);
    }

    @GetMapping("/reminders")
    public List<Reminder> getAllReminders(@AuthenticationPrincipal User user)
    {
        int user_id = user.getId();
        return reminderService.getAllDetails(user_id);
    }

    @GetMapping("/reminders/{id}")
    public Reminder getReminderById(@PathVariable int id,@AuthenticationPrincipal User user){

        int user_id = user.getId();
        return reminderService.getOneDetail(user_id,id);
    }

    @PutMapping("/reminders/{id}")
    public Reminder updateReminderById(@PathVariable int id, @RequestBody Reminder quest, @AuthenticationPrincipal User user){
        int user_id = user.getId();
        return reminderService.updateDetail(id, quest,user_id);
    }

    @DeleteMapping("/reminders/{id}")
    public void deleteReminderById(@PathVariable int id, @AuthenticationPrincipal User user){

        int user_id = user.getId();
        reminderService.deleteOneDetail(user_id,id);
    }

}
