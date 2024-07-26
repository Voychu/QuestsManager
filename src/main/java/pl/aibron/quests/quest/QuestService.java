package pl.aibron.quests.quest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {


    @Autowired
    private QuestRepository questRepository;


    public Quest saveDetails(Quest quest){
        return questRepository.save(quest);
    }

    public List<Quest> getAllDetails(int userId){
        return questRepository.findAllQuestsByUserId(userId);
    }

    public Quest getOneDetail(int userId, int id) {
        Optional<Quest> quest = questRepository.findQuestByUserId(userId,id);
        if (quest.isPresent()) {
            return quest.get();
        } else {
            throw new QuestNotFoundException("Quest not found with id " + id);
        }
    }

    public Quest updateDetail(int id, Quest quest, int userId){
       Quest existingQuest = getOneDetail(userId, id);
       existingQuest.setQuest_name(quest.getQuest_name());
       existingQuest.setPriority(quest.getPriority());
       existingQuest.setDue_date(quest.getDue_date());
       return questRepository.save(existingQuest);
    }

    public void deleteOneDetail(int userId, int id){
        Optional<Quest> quest = questRepository.findQuestByUserId(userId,id);
        if (quest.isPresent()) {
                questRepository.deleteById(id);
        } else {
            throw new QuestNotFoundException("Quest not found with id " + id);
        }
    }

}
