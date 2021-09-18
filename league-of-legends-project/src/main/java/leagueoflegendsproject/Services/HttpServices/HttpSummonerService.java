package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HttpSummonerService {
    private RiotHttpClient riotHttpClient;

    public HttpSummonerService(final RiotHttpClient riotHttpClient) {
        this.riotHttpClient = riotHttpClient;
    }

    /**
     * Returns object of Summoner class that contains details about summoner from Riot's API
     * Riot's path: lol/summoner/v4/summoners/by-name/{summonerName}
     *
     * @return Summoner's object that contains details about summoner from Riot's API
    */
    public Summoner getSummonerByName(String nickname)
            throws IOException, InterruptedException {
        nickname = nickname.replace(" ", "%20");
        return riotHttpClient.get("https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + nickname,
                Summoner.class)
                .getResponse();
    }




}
