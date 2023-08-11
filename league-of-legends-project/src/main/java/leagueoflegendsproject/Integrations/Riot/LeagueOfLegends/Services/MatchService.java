package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MatchService {

    private final RiotHttpClient riotHttpClient;
    private final HttpSummonerService summonerService;
    private final HttpSummonerService httpSummonerService;
    @Value(value = "${riot.leagueOfLegends.challengers.numberOfMatches}")
    private Integer challengerNumberOfMatches;

    public CompletableFuture<List<Match>> getMatchCollectionByNickname(String nickname, int numberOfMatches) throws IOException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> summonerService.getSummonerByNameHTTP(nickname).getPuuid())
                .thenApplyAsync(puuid -> getMatchIds(puuid, numberOfMatches)).thenApply(matchesIds -> {
                    var fetchMatches = matchesIds
                            .stream()
                            .map(this::getMatchByIdAsync)
                            .collect(Collectors.toList());

                    return fetchMatches.stream().map(CompletableFuture::join).collect(Collectors.toList());
                });
    }

    private List<String> getMatchIds(String puuid, int numberOfMatches) {
        String[] matchesIds;
        try {
            matchesIds = riotHttpClient.get(
                    RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches),
                    String[].class).getResponse();
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException(
                    "Unable to find suitable records for link: " +
                            RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches));
        }
        return List.of(matchesIds);
    }

    public CompletableFuture<Match> getMatchByIdAsync(String id) {
        return CompletableFuture.supplyAsync(() -> getMatchByIdSync(id));
    }

    @Cacheable(cacheNames = "matchCache", unless = "#result == null", key = "id")
    public Match getMatchByIdSync(String id) {
        HttpResponseWrapper<Match> matchHttpResponseWrapper;
        try {
            matchHttpResponseWrapper = riotHttpClient.get(RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(id),
                    Match.class);
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException("Unable to find suitable records for link: " +
                    RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(id));
        }
        if (!matchHttpResponseWrapper.isSuccess())
            throw new IllegalStateException("Data from Riot's API cannot be retrieved");
        return matchHttpResponseWrapper.getResponse();
    }

    public List<Match> getChallengerMatches() throws IOException, InterruptedException {
        List<String> nicknames = httpSummonerService.getSummonerChallengersNicknamesHTTP();
        List<CompletableFuture<List<Match>>> asyncMatches = nicknames.stream()
                .map(nickname -> CompletableFuture.supplyAsync(() -> {
                    List<Match> matches;
                    try {
                        matches = getMatchCollectionByNickname(nickname, challengerNumberOfMatches).join();
                    } catch (IOException | InterruptedException e) {
                        log.error("Couldn't get challengers matches for nickname: " + nickname);
                        matches = new ArrayList<>();
                    }
                    return matches;
                }))
                .collect(Collectors.toList());

        return asyncMatches.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
