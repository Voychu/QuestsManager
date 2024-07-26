package pl.aibron.quests.reminder;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String fromPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        Twilio.init(accountSid, authToken);

        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                messageBody
        ).create();
    }

    @Autowired
    private ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
    }

    public Reminder saveDetails(Reminder reminder){
        return reminderRepository.save(reminder);
    }

    public List<Reminder> getAllDetails(int userId){
        return reminderRepository.findAllRemindersByUserId(userId);
    }

    public Reminder getOneDetail(int userId, int id) {
        Optional<Reminder> reminder = reminderRepository.findReminderByUserId(userId, id);
        if (reminder.isPresent()) {
            return reminder.get();
        } else {
            throw new ReminderNotFoundException("Reminder not found with id " + id);
        }
    }

    public Reminder updateDetail(int id, Reminder reminder, int userId){
        Reminder existingReminder = getOneDetail(userId, id);
        existingReminder.setReminderDateTime(reminder.getReminderDateTime());
        return reminderRepository.save(existingReminder);
    }

    public void deleteOneDetail(int userId, int id){
        Optional<Reminder> reminder = reminderRepository.findReminderByUserId(userId,id);
        if (reminder.isPresent()) {
            reminderRepository.deleteById(id);
        } else {
            throw new ReminderNotFoundException("Reminder not found with id " + id);
        }
    }

    public void deleteForScheduler(int id){
        reminderRepository.deleteById(id);
    }

    public List<Reminder> getAllForScheduler(){
        return reminderRepository.findAll();
    }
}
