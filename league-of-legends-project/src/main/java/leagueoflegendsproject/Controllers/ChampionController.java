package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Services.DbServices.DbChampionService;
import leagueoflegendsproject.Services.HttpServices.HttpChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/champion")
public class ChampionController {

    DbChampionService dbChampionService;
    private final HttpChampionService httpChampionService;


    public ChampionController(DbChampionService dbChampionService,
                              HttpChampionService httpChampionService) {
        this.dbChampionService = dbChampionService;
        this.httpChampionService = httpChampionService;
    }

    @GetMapping("/strongAganist/{championName}")
    public ResponseEntity<?> getStrongChampionsWithWinRatio(@PathVariable String championName) {
        var response = dbChampionService.getChampionsStrongAgainst(championName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/weakAganist/{championName}")
    public ResponseEntity<?> getWeakChampionsWithWinRatio(@PathVariable String championName) {
        var response = dbChampionService.getChampionsWeakAgainst(championName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshChampions() throws IOException, InterruptedException {
        var championItems = httpChampionService.getChampionList();
        Set<Champion> champions = championItems.stream()
                .map(dbChampionService::saveChampion)
                .collect(Collectors.toSet());
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public ResponseEntity<?> getChampionsDetails() {
        var response = dbChampionService.getShortChampionDetails();
        return ResponseEntity.ok(response);
    }

}
