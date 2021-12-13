package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.SummonersLeagueDto;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeague;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import leagueoflegendsproject.Services.DbServices.DbMatchService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        String url = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + nickname;
        return riotHttpClient.get(url, Summoner.class)
                .getResponse();
    }

    public SummonersLeagueDto getSummonerLeagueByNickname(String nickname)
            throws IOException, InterruptedException {
        Summoner summoner = getSummonerByName(nickname);
        String summonerEncryptedId = summoner.getId();
        String url = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerEncryptedId;
        var response =  riotHttpClient.get(url, SummonerLeagueResponseItem[].class)
                .getResponse();
        return new SummonersLeagueDto(response);
    }


}
