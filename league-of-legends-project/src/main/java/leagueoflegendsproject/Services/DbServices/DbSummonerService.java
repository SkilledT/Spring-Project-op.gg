package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.SummonersLeagueDto;
import leagueoflegendsproject.Models.Database.Summoner;
import leagueoflegendsproject.Repositories.SummonerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbSummonerService {
    private final SummonerRepository summonerRepository;

    public DbSummonerService(SummonerRepository summonerRepository) {
        this.summonerRepository = summonerRepository;
    }

    public Summoner addSummoner(Summoner summoner) {
        return this.summonerRepository.save(summoner);
    }

    public List<SummonersLeagueDto> getSummonerByTier(String tier) {
        return summonerRepository.findSummonerByTier(tier)
                .stream()
                .map(SummonersLeagueDto::new)
                .collect(Collectors.toList());
    }

}
