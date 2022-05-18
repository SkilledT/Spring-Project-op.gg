package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Services.DbServices.DbChampionService;
import leagueoflegendsproject.Services.DbServices.DbMatchService;
import leagueoflegendsproject.Services.HttpServices.HttpChampionService;
import leagueoflegendsproject.Services.HttpServices.HttpMatchService;
import leagueoflegendsproject.Services.HttpServices.HttpPerkService;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

    private final HttpMatchService matchService;
    private final HttpChampionService httpChampionService;
    private final DbMatchService dbMatchService;
    private final DbChampionService dbChampionService;
    private final HttpSummonerService httpSummonerService;

    public MatchController(final HttpMatchService matchService,
                           final DbMatchService dbMatchService,
                           final HttpChampionService httpChampionService,
                           final HttpSummonerService httpSummonerService,
                           final HttpPerkService httpPerkService,
                           final DbChampionService dbChampionService) {
        this.matchService = matchService;
        this.dbMatchService = dbMatchService;
        this.httpChampionService = httpChampionService;
        this.dbChampionService = dbChampionService;
        this.httpSummonerService = httpSummonerService;
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<?> getAllMatchesByNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(dbMatchService.getMatchesByNickname(nickname));
    }

    @GetMapping("/championStats/{nickname}")
    public ResponseEntity<?> getChampionStatsByNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(dbMatchService.getChampionStatsByNickname(nickname));
    }

    @GetMapping("/details/{nickname}")
    public ResponseEntity<?> getPlayerMatchDetailsList(@PathVariable String nickname) {
        return ResponseEntity.ok(dbMatchService.getMatchesByNickname(nickname));
    }

    @GetMapping("/rolePreferences/{nickname}")
    public ResponseEntity<?> getSummonersPreferredRole(@PathVariable String nickname) {
        return ResponseEntity.ok(dbMatchService.getPreferredRole(nickname));
    }

    @GetMapping("/refresh/{nickname}")
    public ResponseEntity<?> refreshData(@PathVariable String nickname) throws IOException, InterruptedException {
        return ResponseEntity.ok(matchService.getMatchCollectionByNickname(nickname, 3));
    }

    @GetMapping("/refresh/champion")
    public ResponseEntity<?> refreshChampions() throws IOException, InterruptedException {
        var championItems = httpChampionService.getChampionList();
        Set<Champion> champions = championItems.stream()
                .map(dbChampionService::saveChampion)
                .collect(Collectors.toSet());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/refresh/challengers")
    public ResponseEntity<?> refreshChallengersData() {
        new Thread(() -> {
            try {
                getMatchData();
                System.out.println("Retrieving challengers data has been completed");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return ResponseEntity.ok("ok");
    }

    private synchronized void getMatchData() throws IOException, InterruptedException {
        var nicknames = httpSummonerService.getSummonerChallengersNicknamesHTTP();
        nicknames.forEach(nickname -> {
            try {
                matchService.getMatchCollectionByNickname(nickname, 5);
                wait(1000 * 60);
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

    }
}
