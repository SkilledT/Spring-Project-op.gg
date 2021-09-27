package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Services.HttpServices.HttpMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

    private final HttpMatchService matchService;

    public MatchController(final HttpMatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<List<Match>> getAllMatchesByNickname(@PathVariable String nickname){
        try {
            var matches = matchService.getMatchCollectionByNickname(nickname);
            return ResponseEntity.ok(matches);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/championStats/{nickname}")
    public ResponseEntity<?> getChampionStatsByNickname(@PathVariable String nickname){
        try {
            var matches = matchService.getChampionStatsByNickname(nickname);
            return ResponseEntity.ok(matches);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/details/{nickname}")
    public ResponseEntity<?> getPlayerMatchDetailsList(@PathVariable String nickname){
        try {
            var matches = matchService.getPlayerMatchDetailsList(nickname);
            return ResponseEntity.ok(matches);
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rolePreferences/{nickname}")
    public ResponseEntity<?> getSummonersPreferredRole(@PathVariable String nickname){
        try {
            var matches = matchService.getSummonersPreferredRole(nickname);
            return ResponseEntity.ok(matches);
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
