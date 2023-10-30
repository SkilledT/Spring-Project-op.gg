package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.ChallengersByQueue.EntriesItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.ChallengersByQueue.Response;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Summoner.SummonerName.Summoner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HttpSummonerService {
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

    public List<String> getSummonerChallengersNicknamesHTTP() throws IOException, InterruptedException {
        String url = RiotLinksProvider.SummonerLinksProvider.RIOT_CHALLENGERS_URL;
        return riotHttpClient.get(url, Response.class).getResponse()
                .getEntries()
                .stream()
                .map(EntriesItem::getSummonerName)
                .collect(Collectors.toList());
    }
}
