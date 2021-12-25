package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.DTOs.PerkDto;
import leagueoflegendsproject.Repositories.Interfaces.ChampionRepositoryCustom;
import leagueoflegendsproject.Repositories.MatchParticipantPerkRepository;
import leagueoflegendsproject.Repositories.PerkRepository;
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

    MatchParticipantPerkRepository matchParticipantPerkRepository;
    PerkRepository perkRepository;

    public PerkController(MatchParticipantPerkRepository matchParticipantPerkRepository,
                          PerkRepository perkRepository) {
        this.matchParticipantPerkRepository = matchParticipantPerkRepository;
        this.perkRepository = perkRepository;
    }

    @GetMapping("/mainTree/{championName}")
    public ResponseEntity<?> getMainTreeByChampionName(@PathVariable String championName){
        List<PerkDto> perkDtoList = perkRepository
                .findMostPopularPerksMainTreeForChampionByItsName(championName)
                .stream()
                .map(PerkDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(perkDtoList);
    }

    @GetMapping("/subTree/{championName}")
    public ResponseEntity<?> getSubTreeByChampionName(@PathVariable String championName){
        List<PerkDto> perkDtoList = perkRepository
                .findMostPopularPerksSubTreeForChampionByItsName(championName)
                .stream()
                .map(PerkDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(perkDtoList);
    }
}
