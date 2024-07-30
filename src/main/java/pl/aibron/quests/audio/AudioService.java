package pl.aibron.quests.audio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AudioService {


    @Autowired
    private AudioRepository audioRepository;


    public Audio saveDetails(Audio quest){
        return audioRepository.save(quest);
    }

    public List<Audio> getAllDetails(int userId){
        return audioRepository.findAllAudiosByUserId(userId);
    }

    public Audio getOneDetail(int userId, int id) {
        Optional<Audio> quest = audioRepository.findAudioByUserId(userId,id);
        if (quest.isPresent()) {
            return quest.get();
        } else {
            throw new AudioNotFoundException("Audio not found with id " + id);
        }
    }

    public Audio updateDetail(int id, Audio quest, int userId){
        Audio existingAudio = getOneDetail(userId, id);
        existingAudio.setAudioData(quest.getAudioData());
        return audioRepository.save(existingAudio);
    }

    public void deleteOneDetail(int userId, int id){
        Optional<Audio> quest = audioRepository.findAudioByUserId(userId,id);
        if (quest.isPresent()) {
            audioRepository.deleteById(id);
        } else {
            throw new AudioNotFoundException("Audio not found with id " + id);
        }
    }

}