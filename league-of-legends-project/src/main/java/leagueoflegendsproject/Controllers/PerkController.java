package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.DTOs.PerkDto;
import leagueoflegendsproject.Repositories.ChampionPerkRepository;
import leagueoflegendsproject.Repositories.Interfaces.ChampionRepositoryCustom;
import leagueoflegendsproject.Repositories.MatchParticipantPerkRepository;
import leagueoflegendsproject.Repositories.PerkRepository;
import leagueoflegendsproject.Services.DbServices.DbPerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/perk")
public class PerkController {

    DbPerkService dbPerkService;


    public PerkController(DbPerkService dbPerkService) {
        this.dbPerkService = dbPerkService;
    }

    @GetMapping("/tree/{championName}/{type}")
    public ResponseEntity<?> getPerkTreeByChampionNameAndType(@PathVariable String championName, @PathVariable String type){
        return ResponseEntity.ok(dbPerkService.getPerkByChampionNameAndTreeType(championName, type));
    }

}
