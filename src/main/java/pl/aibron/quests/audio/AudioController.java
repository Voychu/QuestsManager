package pl.aibron.quests.audio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.aibron.quests.audio.AudioService;
import pl.aibron.quests.user.User;
import pl.aibron.quests.audio.Audio;

import java.util.List;

@RestController
public class AudioController {

    @Autowired
    private AudioService audioService;

    @PostMapping("/audios")
    public Audio postAudio(@RequestBody Audio audio, @AuthenticationPrincipal User user){
        audio.setUser(user);
        return audioService.saveDetails(audio);
    }


    @GetMapping("/audios")
    public List<Audio> getAllAudios(@AuthenticationPrincipal User user){

        int user_id = user.getId();
        return audioService.getAllDetails(user_id);
    }

    @GetMapping("/audios/{id}")
    public Audio getAudioById(@PathVariable int id, @AuthenticationPrincipal User user){

        int user_id = user.getId();
        return audioService.getOneDetail(user_id,id);
    }

    @PutMapping("/audios/{id}")
    public Audio updateAudioById(@PathVariable int id, @RequestBody Audio audio, @AuthenticationPrincipal User user){

        int user_id = user.getId();
        return audioService.updateDetail(id, audio, user_id);
    }

    @DeleteMapping("/audios/{id}")
    public void deleteAudioById(@PathVariable int id,  @AuthenticationPrincipal User user){

        int user_id = user.getId();
        audioService.deleteOneDetail(user_id, id);
    }
}
