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
    private final DbMatchService dbMatchService;

    public MatchController(final HttpMatchService matchService,
                           final DbMatchService dbMatchService) {
        this.matchService = matchService;
        this.dbMatchService = dbMatchService;
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
        return ResponseEntity.ok(matchService.getMatchCollectionByNickname(nickname, 10));
    }
}
