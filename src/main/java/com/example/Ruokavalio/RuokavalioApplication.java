package com.example.Ruokavalio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Ruokavalio.domain.Ravinto;
import com.example.Ruokavalio.domain.RavintoRepository;
import com.example.Ruokavalio.domain.User;
import com.example.Ruokavalio.domain.UserRepository;

@SpringBootApplication
public class RuokavalioApplication {

	private static final Logger log = LoggerFactory.getLogger(RuokavalioApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RuokavalioApplication.class, args);
	}
	
	
	// Lisätään päivän oletus ravinto
	@Bean
	public CommandLineRunner demo(RavintoRepository repository, UserRepository urepository) {
		return (args) -> {
			log.info("Lisätään päivän oletus ravinto");
			
			repository.save(new Ravinto("Pikapuuro", 70 , 266, 40, 8.4));
			repository.save(new Ravinto("Kanamuna", 165, 237.6, 0.5, 20.6));
			repository.save(new Ravinto("Riisi", 187.5, 660, 144, 13.8));
			repository.save(new Ravinto("Kana", 200, 247.5, 0, 54));
			repository.save(new Ravinto("Palautusjuoma", 50.0, 195, 27.7, 17.8));
			repository.save(new Ravinto("Pähkinöitä", 100, 609, 24, 23));
			
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			};
	}

}
