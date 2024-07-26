package pl.aibron.quests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestsApplication.class, args);
	}

}
