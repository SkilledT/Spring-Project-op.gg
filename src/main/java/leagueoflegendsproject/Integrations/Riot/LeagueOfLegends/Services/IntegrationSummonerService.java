package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Summoner.SummonerName.Summoner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@Service
public class IntegrationSummonerService {

    private final RiotHttpClient riotHttpClient;

    // Returns simple summoner object without LP, Tier, Rank and so on
    public Summoner getSummonerByNameHTTP(String nickname) {
        nickname = nickname.replace(" ", "%20");
        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerByNicknameUrl(nickname);
        HttpResponseWrapper<Summoner> responseWrapper = null;
        try {
            responseWrapper = riotHttpClient.get(url, Summoner.class);
        } catch (IOException | InterruptedException e) {
            log.error("Unable to retrieve object, message: " + e.getMessage());
        }
        if (responseWrapper == null || !responseWrapper.isSuccess())
            log.error("Unable to retrieve object, message: " + responseWrapper.getResponseMessage());
        return responseWrapper.getResponse();
    }

    // returns all data with LP, Tier, Rank and so on [list of items by queueType - (solo/duo = RANKED_SOLO_5X5)]
    public SummonerLeagueResponseItem[] getSummonerLeagueByNicknameHTTP(String nickname) {
        Summoner summoner = getSummonerByNameHTTP(nickname);
        String summonerEncryptedId = summoner.getId();
        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerLeagueByNicknameUrl(summonerEncryptedId);
        HttpResponseWrapper<SummonerLeagueResponseItem[]> responseWrapper;
        try {
            responseWrapper = riotHttpClient.get(url, SummonerLeagueResponseItem[].class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Unable to retrieve object, message: " + e.getMessage());
        }
        if (!responseWrapper.isSuccess() || responseWrapper.getResponse() == null)
            throw new IllegalArgumentException("Unable to retrieve object, message: " + responseWrapper.getResponseMessage());
        return responseWrapper.getResponse();
    }
}
