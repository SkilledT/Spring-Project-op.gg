package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Summoner;
import leagueoflegendsproject.v2.Models.SummonerSnapshot;
import leagueoflegendsproject.v2.Repositories.SummonerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SummonerService {

    private final Integer SUMMONER_DEFAULT_LEVEL = 30;
    private final Integer SUMMONER_DEFAULT_PROFILE_ID = 0;

    private final SummonerRepository summonerRepository;
    private final SummonerSnapshotService summonerSnapshotService;

    private Summoner createSummoner(String summonerId, Integer lvl, Integer profileIconId) {
        return new Summoner()
                .setSummonerId(summonerId)
                .setLvl(lvl)
                .setProfileIconId(profileIconId);
    }

    public Optional<Summoner> updateSummoner(String summonerId, Integer lvl, Integer profileIconId, String summonerNickname,
                                   String tier, String rank, Integer leaguePoints, Integer wins, Integer losses) {
        Optional<Summoner> summoner = findSummonerBySummonerId(summonerId);
        if (summoner.isEmpty()) {
            return Optional.empty();
        }
        summoner.get().setLvl(lvl);
        summoner.get().setProfileIconId(profileIconId);

        if (isNewSnapshotNeeded(summonerNickname, tier, rank)) {
            SummonerSnapshot snapshot = summonerSnapshotService
                    .createAndSaveSummonerSnapshot(summonerNickname, tier, rank, leaguePoints,
                            wins, losses, summoner.get());
            summoner.get().getSummonerSnapshots().add(snapshot);
        }

        return Optional.of(
                summonerRepository.save(summoner.get())
        );
    }

    private boolean isNewSnapshotNeeded(String summonerNickname,
                                        String tier, String rank) {
        return !isBlankOrNull(summonerNickname) && !isBlankOrNull(tier) && !isBlankOrNull(rank);
    }

    private boolean isBlankOrNull(String str) {
        return str == null || str.isBlank();
    }

    public Summoner createAndSaveSummoner(String summonerId, Integer lvl, Integer profileIconId, String summonerNickname,
                                          String tier, String rank, Integer leaguePoints, Integer wins, Integer losses) {
        Summoner summoner = createSummoner(summonerId, lvl, profileIconId);

        SummonerSnapshot snapshot;
        if (summonerNickname == null || summonerNickname.isEmpty()) {
            snapshot = summonerSnapshotService
                    .createAndSaveSummonerSnapshot(summonerNickname, null, null, null,
                            null, null, summoner);
        } else {
            snapshot = summonerSnapshotService
                    .createAndSaveSummonerSnapshot(summonerNickname, tier, rank, leaguePoints, wins, losses, summoner);
        }
        summoner.getSummonerSnapshots().add(snapshot);
        return  summonerRepository.save(summoner);
    }

    public Summoner createUnrankedSummoner(String summonerId) {
        return createSummoner(summonerId, SUMMONER_DEFAULT_LEVEL, SUMMONER_DEFAULT_PROFILE_ID);
    }

    public Summoner createAndSaveUnrankedSummoner(String summonerId, String summonerNickname) {
        Summoner summoner = createUnrankedSummoner(summonerId);
        SummonerSnapshot snapshot = summonerSnapshotService
                .createAndSaveSummonerSnapshot(summonerNickname, null, null, null,
                        null, null, summoner);
        snapshot.setSummoner(summoner);
        summoner.getSummonerSnapshots().add(snapshot);
        return summonerRepository.save(summoner);
    }

    public Optional<Summoner> findSummonerBySummonerId(String summonerId) {
        return summonerRepository.findSummonerBySummonerId(summonerId);
    }
}










