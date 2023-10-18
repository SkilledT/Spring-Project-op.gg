package leagueoflegendsproject.v2.Processors;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services.IntegrationSummonerService;
import leagueoflegendsproject.v2.Models.Summoner;
import leagueoflegendsproject.v2.Services.SummonerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SummonerProcessor {

    private final SummonerService summonerService;
    private final IntegrationSummonerService httpSummonerService;

    public Optional<Summoner> fetchAndUpdateSummonerOrCreate(String summonerNickname) {
        log.info("======================= START UPDATING SUMMONER {} =======================", summonerNickname);
        Optional<SummonerLeagueResponseItem> summonerItem =
                findByQueueType(httpSummonerService.getSummonerLeagueByNicknameHTTP(summonerNickname),
                        SummonerLeagueResponseItem.QueueTypeEnum.RANKED_SOLO_5x5);
        if (summonerItem.isEmpty()) {
            // TODO: THROW AN EXCEPTION THAT REQUEST WAS FAILED
            return Optional.empty();
        }
        Optional<Summoner> summoner = summonerService
                .findSummonerBySummonerId(summonerItem.get().getSummonerId());
        var item = summonerItem.get();
        if (summoner.isEmpty()) {
            return Optional.of(
                    summonerService.createAndSaveSummoner(
                            item.getSummonerId(), null, null, item.getSummonerName(), item.getTier(),
                            item.getRank(), item.getLeaguePoints(), item.getWins(), item.getLosses()
                    )
            );
        }
        return summonerService.updateSummoner(
                item.getSummonerId(), summoner.get().getLvl(), summoner.get().getProfileIconId(), item.getSummonerName(),
                item.getTier(), item.getRank(), item.getLeaguePoints(), item.getWins(), item.getLosses()
        );
    }

    private Optional<SummonerLeagueResponseItem> findByQueueType(SummonerLeagueResponseItem[] summonerLeagueResponseItems,
                                                                 SummonerLeagueResponseItem.QueueTypeEnum queueTypeEnum) {
        return Arrays.stream(summonerLeagueResponseItems)
                .filter(e -> e.getQueueType().equals(queueTypeEnum.name()))
                .findFirst();
    }
}
