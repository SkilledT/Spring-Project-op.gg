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
    public ResponseEntity<?> getChampionsWithWinRatio(@PathVariable String championName){
        var response = championService.getChampionWithWinRatioEntity(championName, 3);
        return ResponseEntity.ok(response);
    }
}
