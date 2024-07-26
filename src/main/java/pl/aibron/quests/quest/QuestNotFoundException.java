package pl.aibron.quests.quest;

public class QuestNotFoundException extends RuntimeException {

    public QuestNotFoundException(String message) {
        super(message);
    }
}