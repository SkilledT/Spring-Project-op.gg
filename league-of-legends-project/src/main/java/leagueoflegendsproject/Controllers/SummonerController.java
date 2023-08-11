package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.DTOs.SummonersLeagueDto;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import leagueoflegendsproject.Repositories.SummonerRepository;
import leagueoflegendsproject.Services.DbServices.DbSummonerService;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/summoner")
public class SummonerController {

    private final HttpSummonerService httpSummonerService;
    private final DbSummonerService dbSummonerService;
    private final SummonerRepository summonerRepository;

    public SummonerController(final HttpSummonerService httpSummonerService,
                              final DbSummonerService dbSummonerService, SummonerRepository summonerRepository) {
        this.httpSummonerService = httpSummonerService;
        this.dbSummonerService = dbSummonerService;
        this.summonerRepository = summonerRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getSummonerByName() {
        String name = "SSW Miszi M";
        Summoner summoner = httpSummonerService.getSummonerByNameHTTP(name);
        return ResponseEntity.ok(summoner);
    }

    @GetMapping("/league/{summonerName}")
    public ResponseEntity<?> getSummonerLeagueByNickname(@PathVariable String summonerName) {
        String name = "Gande";
        SummonersLeagueDto summoner = httpSummonerService.getSummonerLeagueByNicknameHTTP(summonerName);
        return ResponseEntity.ok(summoner);
    }

    @GetMapping("/challengers")
    public ResponseEntity<?> getChallengers() {
        String tier = "Challenger";
        var challengers = dbSummonerService.getSummonerByTier(tier);
        return ResponseEntity.ok(challengers);
    }

    @GetMapping("/refresh/challengers")
    public ResponseEntity<?> refreshChallengers() throws IOException, InterruptedException {
        var challengers = httpSummonerService.updateChallengers();
        return ResponseEntity.ok(challengers);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(summonerRepository.findAllPageSummoners(PageRequest.of(0, 2)));
    }
}
