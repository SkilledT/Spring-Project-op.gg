package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
        String name = "SSW Miszi M";
        try {
            var summoner = httpSummonerService.getSummonerByName(name);
            return ResponseEntity.ok(summoner);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/league")
    public ResponseEntity<?> getSummonerLeagueByNickname(){
        String name = "SSW Miszi M";
        try {
            var summoner = httpSummonerService.getSummonerLeagueByNickname(name);
            return ResponseEntity.ok(summoner);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
