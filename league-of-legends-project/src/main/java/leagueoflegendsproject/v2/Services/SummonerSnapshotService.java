package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import leagueoflegendsproject.v2.Models.Summoner;
import leagueoflegendsproject.v2.Models.SummonerSnapshot;
import leagueoflegendsproject.v2.Repositories.SummonerSnapshotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SummonerSnapshotService {

    private final SummonerSnapshotRepository summonerSnapshotRepository;

    public SummonerSnapshot createSummonerSnapshot(String summonerNickname,
                                                   String tier,
                                                   String rank,
                                                   Integer leaguePoints,
                                                   Integer wins,
                                                   Integer losses,
                                                   Summoner summoner) {
        return new SummonerSnapshot()
                .setSummonerNickname(summonerNickname)
                .setTier(tier)
                .setRank(rank)
                .setLeaguePoints(leaguePoints)
                .setWins(wins)
                .setLosses(losses)
                .setSummoner(summoner);
    }

    public SummonerSnapshot createAndSaveSummonerSnapshot(String summonerNickname,
                                                          String tier,
                                                          String rank,
                                                          Integer leaguePoints,
                                                          Integer wins,
                                                          Integer losses,
                                                          Summoner summoner) {
        return summonerSnapshotRepository.save(
                createSummonerSnapshot(summonerNickname, tier, rank, leaguePoints, wins, losses, summoner)
        );
    }

    public Optional<SummonerSnapshot> findNewestSummonerSnapshot(Summoner summoner) {
        return summoner.getSummonerSnapshots()
                .stream()
                .max(Comparator.comparing(SummonerSnapshot::getCreatedAt));

    }

}


