package pl.aibron.quests.reminder;

public class ReminderNotFoundException extends RuntimeException {

    public ReminderNotFoundException(String message) {
        super(message);
    }
}