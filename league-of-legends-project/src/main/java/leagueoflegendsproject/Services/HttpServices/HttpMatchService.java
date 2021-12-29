package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.*;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Services.DbServices.DbMatchService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpMatchService {

    private final RiotHttpClient riotHttpClient;
    private final HttpSummonerService summonerService;
    private final DbMatchService dbMatchService;

    public HttpMatchService(final RiotHttpClient riotHttpClient,
                            final HttpSummonerService summonerService,
                            final DbMatchService dbMatchService) {
        this.riotHttpClient = riotHttpClient;
        this.summonerService = summonerService;
        this.dbMatchService = dbMatchService;
    }


    public List<Match> getMatchCollectionByNickname(String nickname, int numberOfMatches) throws IOException, InterruptedException {
        String puuid = summonerService.getSummonerByName(nickname).getPuuid();
        HttpResponseWrapper<String[]> matchesId = riotHttpClient.get(
                RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches),
                        String[].class);
        if (!matchesId.isSuccess())
                throw new IllegalStateException("Data from Riot's API cannot be retried");
        String[] matchesIds = matchesId.getResponse();
        List<Match> matches = new ArrayList<>();
        for (String id : matchesIds){
            Match match = getMatchById(id);
            dbMatchService.AddMatchToDb(match);
            matches.add(match);
        }
        return matches;
    }


    Match getMatchById(String id) throws IOException, InterruptedException {
        HttpResponseWrapper<Match> matchHttpResponseWrapper =
                riotHttpClient.get(RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(id),
                        Match.class);
        if (!matchHttpResponseWrapper.isSuccess())
            throw new IllegalStateException("Data from Riot's API cannot be retried");
        return matchHttpResponseWrapper.getResponse();
    }


}
