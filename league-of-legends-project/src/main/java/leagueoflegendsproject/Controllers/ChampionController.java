package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Services.DbServices.DbChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/champion")
public class ChampionController {

    DbChampionService championService;

    public ChampionController(DbChampionService championService) {
        this.championService = championService;
    }

    @GetMapping("/strongAganist/{championName}")
    public ResponseEntity<?> getStrongChampionsWithWinRatio(@PathVariable String championName) {
        var response = championService.getChampionsStrongAgainst(championName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/weakAganist/{championName}")
    public ResponseEntity<?> getWeakChampionsWithWinRatio(@PathVariable String championName) {
        var response = championService.getChampionsWeakAgainst(championName);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getChampionsDetails() {
        var response = championService.getShortChampionDetails();
        return ResponseEntity.ok(response);
    }

}
