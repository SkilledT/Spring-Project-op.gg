package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import leagueoflegendsproject.Services.DbServices.DbMatchService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpMatchService {

    private final RiotHttpClient riotHttpClient;
    private final HttpSummonerService summonerService;
    private final DbMatchService dbMatchService;
    private final HttpSummonerService httpSummonerService;


    public HttpMatchService(final RiotHttpClient riotHttpClient,
                            final HttpSummonerService summonerService,
                            final DbMatchService dbMatchService, HttpSummonerService httpSummonerService) {
        this.riotHttpClient = riotHttpClient;
        this.summonerService = summonerService;
        this.dbMatchService = dbMatchService;
        this.httpSummonerService = httpSummonerService;
    }


    public CompletableFuture<List<Match>> getMatchCollectionByNickname(String nickname, int numberOfMatches) throws IOException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> summonerService.getSummonerByNameHTTP(nickname).getPuuid())
                .thenApplyAsync(puuid -> {
                    String[] matchesIds;
                    try {
                        matchesIds = riotHttpClient.get(
                                RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches),
                                String[].class).getResponse();
                    } catch (IOException | InterruptedException e) {
                        throw new IllegalArgumentException("Unable to find suitable records for link: " + RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches));
                    }
                    return matchesIds;
                }).thenApply(matchesIds -> {
                    List<Match> matches = new ArrayList<>();
                    Arrays.stream(matchesIds).forEach(matchId -> {
                        Match match = getMatchById(matchId).join();
                        dbMatchService.addMatchToDb(match);
                        matches.add(match);
                    });
                    return matches;
                });
    }


    CompletableFuture<Match> getMatchById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            HttpResponseWrapper<Match> matchHttpResponseWrapper;
            try {
                matchHttpResponseWrapper = riotHttpClient.get(RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(id),
                        Match.class);
            } catch (IOException | InterruptedException e) {
                throw new IllegalArgumentException("Unable to find suitable records for link: " + RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(id));
            }
            if (!matchHttpResponseWrapper.isSuccess())
                throw new IllegalStateException("Data from Riot's API cannot be retried");
            return matchHttpResponseWrapper.getResponse();
        });
    }

    public void refreshChallengersData() {
        new Thread(() -> {
            try {
                getMatchData();
                System.out.println("Retrieving challengers data has been completed");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private synchronized void getMatchData() throws IOException, InterruptedException {
        var nicknames = httpSummonerService.getSummonerChallengersNicknamesHTTP();
        nicknames.forEach(nickname -> {
            try {
                getMatchCollectionByNickname(nickname, 5);
                wait(1000 * 60);
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
    }


}
