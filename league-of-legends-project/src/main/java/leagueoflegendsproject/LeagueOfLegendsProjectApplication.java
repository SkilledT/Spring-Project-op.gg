package leagueoflegendsproject;

import leagueoflegendsproject.Filters.CorsFilter;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class LeagueOfLegendsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueOfLegendsProjectApplication.class, args);
	}

}
