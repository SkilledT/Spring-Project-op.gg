package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HttpMatchService {

    private final RiotHttpClient riotHttpClient;
    private final HttpSummonerService summonerService;

    public HttpMatchService(final RiotHttpClient riotHttpClient,
                            final HttpSummonerService summonerService) {
        this.riotHttpClient = riotHttpClient;
        this.summonerService = summonerService;
    }

    public List<Match> getMatchCollectionByNickname(String nickname) throws IOException, InterruptedException {
        String puuid = summonerService.getSummonerByName(nickname).getPuuid();
        HttpResponseWrapper<String[]> matchesId =
                riotHttpClient.get("https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=20",
                        String[].class);
        String[] matchesIds = matchesId.getResponse();
        List<Match> matches = new ArrayList<>();
        for (String id : matchesIds){
            Match match = getMatchById(id);
            matches.add(match);
        }
        return matches;
    }

    public Match getMatchById(String id) throws IOException, InterruptedException {
        HttpResponseWrapper<Match> matchHttpResponseWrapper =
                riotHttpClient.get("https://europe.api.riotgames.com/lol/match/v5/matches/" + id,
                        Match.class);
        return matchHttpResponseWrapper.getResponse();
    }

    public List<PlayersChampionStatsDto> getChampionStatsByNickname(String nickname) throws IOException, InterruptedException {
        List<Match> matches = getMatchCollectionByNickname(nickname);
        String userPuuid = summonerService.getSummonerByName(nickname).getPuuid();

        List<ParticipantsItem> participants = new ArrayList<>();
        List<List<ParticipantsItem>> lists = matches.stream()
                .map(e -> e.getInfo().getParticipants())
                .collect(Collectors.toList());
        for (var list : lists){
            participants.addAll(list);
        }
        participants = participants.stream()
                .filter(e -> e.getPuuid().equals(userPuuid))
                .collect(Collectors.toList());
        Map<String, List<ParticipantsItem>> byChampName = participants.stream().collect(
                Collectors.groupingBy(ParticipantsItem::getChampionName)
        );

        List<PlayersChampionStatsDto> playersChampionStatsDtos = new ArrayList<>();
        for (var key : byChampName.keySet()){
            var values = byChampName.get(key);
            double winRatio = countWinRatio(values);
            String champName = key;
            double CS = values.stream()
                    .mapToDouble(ParticipantsItem::getTotalMinionsKilled)
                    .average()
                    .orElse(Double.NaN);
            double avgKills = values.stream()
                    .mapToDouble(ParticipantsItem::getKills)
                    .average()
                    .orElse(Double.NaN);
            double avgDeaths = values.stream()
                    .mapToDouble(ParticipantsItem::getDeaths)
                    .average()
                    .orElse(Double.NaN);
            double avgAssists = values.stream()
                    .mapToDouble(ParticipantsItem::getAssists)
                    .average()
                    .orElse(Double.NaN);
            int playedMatches = values.size();
            PlayersChampionStatsDto playersChampionStatsDto =
                    new PlayersChampionStatsDto(winRatio, champName, CS, playedMatches, avgKills, avgDeaths, avgAssists);
            playersChampionStatsDtos.add(playersChampionStatsDto);
        }
        return playersChampionStatsDtos;
    }

    private double countWinRatio(List<ParticipantsItem> participantsItems){
        long victories = participantsItems.stream()
                .filter(ParticipantsItem::isWin)
                .count();
        long playedGames = participantsItems.size();
        return (double)victories/(double)playedGames;
    }

}
