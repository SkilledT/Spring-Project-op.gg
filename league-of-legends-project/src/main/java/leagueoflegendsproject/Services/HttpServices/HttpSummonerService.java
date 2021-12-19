package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.SummonersLeagueDto;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.League.ChallengersByQueue.EntriesItem;
import leagueoflegendsproject.Models.LoLApi.League.ChallengersByQueue.Response;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpSummonerService {
    private final RiotHttpClient riotHttpClient;

    public HttpSummonerService(final RiotHttpClient riotHttpClient) {
        this.riotHttpClient = riotHttpClient;
    }

    public Summoner getSummonerByName(String nickname)
            throws IOException, InterruptedException {
        nickname = nickname.replace(" ", "%20");
        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerByNicknameUrl(nickname);
        return riotHttpClient.get(url, Summoner.class)
                .getResponse();
    }

    public SummonersLeagueDto getSummonerLeagueByNickname(String nickname)
            throws IOException, InterruptedException {
        Summoner summoner = getSummonerByName(nickname);
        String summonerEncryptedId = summoner.getId();
        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerLeagueByNicknameUrl(summonerEncryptedId);
        var response =  riotHttpClient.get(url, SummonerLeagueResponseItem[].class)
                .getResponse();
        return new SummonersLeagueDto(response);
    }

    public List<String> getSummonerChallengersNicknames() throws IOException, InterruptedException {
        String url = RiotLinksProvider.SummonerLinksProvider.RIOT_CHALLENGERS_URL;
        return riotHttpClient.get(url, Response.class).getResponse()
                .getEntries()
                .stream()
                .map(EntriesItem::getSummonerName)
                .collect(Collectors.toList());
    }


}
