package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Match;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntegrationMatchService {

    private final RiotHttpClient riotHttpClient;
    private final HttpSummonerService summonerService;
    private final HttpSummonerService httpSummonerService;
    @Value(value = "${riot.leagueOfLegends.challengers.numberOfMatches}")
    private Integer challengerNumberOfMatches;
    private static final int MAX_MATCH_FETCH = 50;

    public List<Match> getMatchCollectionByNicknameSyncByPuuid(String puuid) {
        return getMatchCollectionByNicknameSyncByPuuid(puuid, MAX_MATCH_FETCH);
    }
    public List<Match> getMatchCollectionByNicknameSyncByPuuid(String puuid, int numberOfMatches) {
        var matchesIds = getMatchIds(puuid, numberOfMatches);
        return matchesIds
                .stream()
                .map(this::getMatchByIdSync)
                .collect(Collectors.toList());
    }

    public List<Match> getMatchCollectionByNicknameSyncByNickname(String nickname, int numberOfMatches) {
        var puuid = summonerService.getSummonerByNameHTTP(nickname).getPuuid();
        return getMatchCollectionByNicknameSyncByPuuid(puuid, numberOfMatches);
    }

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
        matchesIds = riotHttpClient.get(
                RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches),
                String[].class).getResponse();
        return List.of(matchesIds);
    }

    public CompletableFuture<Match> getMatchByIdAsync(String id) {
        return CompletableFuture.supplyAsync(() -> getMatchByIdSync(id));
    }

    @Cacheable(cacheNames = "matchCache", unless = "#result == null", key = "id")
    public Match getMatchByIdSync(String id) {
        HttpResponseWrapper<Match> matchHttpResponseWrapper;
        matchHttpResponseWrapper = riotHttpClient.get(RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(id),
                Match.class);
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
