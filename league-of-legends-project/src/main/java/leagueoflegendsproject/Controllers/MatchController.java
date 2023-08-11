package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Services.DbServices.DbMatchService;
import leagueoflegendsproject.Services.HttpServices.HttpMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/match")
@Transactional
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
        return ResponseEntity.ok(dbMatchService.getChampionStatsByNickname(nickname).join());
    }

    @GetMapping("/details/{nickname}")
    public ResponseEntity<?> getPlayerMatchDetailsList(@PathVariable String nickname) {
        return ResponseEntity.ok(dbMatchService.getMatchesByNickname(nickname));
    }

    @GetMapping("/rolePreferences/{nickname}")
    public ResponseEntity<?> getSummonersPreferredRole(@PathVariable String nickname) {
        return ResponseEntity.ok(dbMatchService.getPreferredRole(nickname).join());
    }

    @GetMapping("/refresh/{nickname}")
    public ResponseEntity<?> refreshData(@PathVariable String nickname) throws IOException, InterruptedException {
        return ResponseEntity.ok(matchService.getMatchCollectionByNickname(nickname, 5));
    }
}
