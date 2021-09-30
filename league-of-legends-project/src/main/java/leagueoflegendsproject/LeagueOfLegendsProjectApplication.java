package leagueoflegendsproject;

import leagueoflegendsproject.Filters.CorsFilter;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class LeagueOfLegendsProjectApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(LeagueOfLegendsProjectApplication.class, args);
	}


	@Bean
	public RiotHttpClient getInstanceOfRiotHttpClient(){
		return new RiotHttpClient();
	}

	@Bean
	public CorsFilter getInstanceOfCorsFilter(){return new CorsFilter();}

}
