package pl.aibron.quests.audio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import pl.aibron.quests.audio.Audio;
import pl.aibron.quests.audio.AudioService;
import pl.aibron.quests.user.User;

@Controller
public class WebSocketController {

    @Autowired
    private AudioService audioService;

    @MessageMapping("/sendAudio")
    public void handleAudioMessage(String message, @AuthenticationPrincipal User user) {
        Audio audio = new Audio();
        audio.setAudioData(message);
        audio.setUser(user);
        audioService.saveDetails(audio);
    }
}
