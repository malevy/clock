package net.malevy.clock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClockApplication.class, args);
	}
}
