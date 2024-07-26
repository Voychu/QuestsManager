package pl.aibron.quests.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {


    @Autowired
    private ReminderService reminderService;

    @Scheduled(fixedRate = 10000)
    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        List<Reminder> reminders = reminderService.getAllForScheduler();

        for (Reminder reminder : reminders) {
            if (reminder.getReminderDateTime().isBefore(now)) {
                String phoneNumber = reminder.getQuest_id().getUser().getPhone_number();
                String message = "Reminder for quest: " + reminder.getQuest_id().getQuest_name()
                                    + " the due date is: " + reminder.getQuest_id().getDue_date();
                reminderService.sendSms(phoneNumber, message);
                reminderService.deleteForScheduler(reminder.getId());
            }
        }
    }
}