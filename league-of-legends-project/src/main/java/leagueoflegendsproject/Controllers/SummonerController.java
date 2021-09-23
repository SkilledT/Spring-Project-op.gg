package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/summoner")
public class SummonerController {

    private final HttpSummonerService httpSummonerService;

    public SummonerController(final HttpSummonerService httpSummonerService) {
        this.httpSummonerService = httpSummonerService;
    }

    @GetMapping()
    public ResponseEntity<?> getSummonerByName(){
        String name = "Skilled Teaser";
        try {
            var summoner = httpSummonerService.getSummonerByName(name);
            return ResponseEntity.ok(summoner);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
