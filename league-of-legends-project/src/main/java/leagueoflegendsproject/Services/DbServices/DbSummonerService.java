package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.Models.Database.Summoner;
import leagueoflegendsproject.Repositories.SummonerRepository;
import org.springframework.stereotype.Service;

@Service
public class DbSummonerService {
    private final SummonerRepository summonerRepository;

    public DbSummonerService(SummonerRepository summonerRepository) {
        this.summonerRepository = summonerRepository;
    }

    public Summoner addSummoner(leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner responseSummoner){
        Summoner dbSummoner = new Summoner(responseSummoner);
        return this.summonerRepository.save(dbSummoner);
    }

    public Summoner getSummonerByNickname(String nickname) throws Exception {
        return this.summonerRepository.findSummonerBySummonerNickname(nickname)
                .orElseThrow(() -> new Exception("No such summoner in DB"));
    }
}
