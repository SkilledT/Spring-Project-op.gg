package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.MatchDetailsDto;
import leagueoflegendsproject.DTOs.PlayerGameDto;
import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;
import leagueoflegendsproject.DTOs.PreferedRoleDto;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpMatchService {

    private final RiotHttpClient riotHttpClient;
    private final HttpSummonerService summonerService;

    public HttpMatchService(final RiotHttpClient riotHttpClient,
                            final HttpSummonerService summonerService) {
        this.riotHttpClient = riotHttpClient;
        this.summonerService = summonerService;
    }

    /**
     * Returns list of Match class that contains details about summoner's matches from Riot's API
     * @param  nickname User's nickname used in game
     *
     * @return List of Match class' objects that contains details about summoner's matches from Riot's API
     */
    public List<Match> getMatchCollectionByNickname(String nickname, int numberOfMatches) throws IOException, InterruptedException {
        String puuid = summonerService.getSummonerByName(nickname).getPuuid();
        HttpResponseWrapper<String[]> matchesId = riotHttpClient.get(
                        "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=" + numberOfMatches,
                        String[].class);
        if (!matchesId.isSuccess())
                throw new IllegalStateException("Data from Riot's API cannot be retried");
        String[] matchesIds = matchesId.getResponse();
        List<Match> matches = new ArrayList<>();
        for (String id : matchesIds){
            Match match = getMatchById(id);
            matches.add(match);
        }
        return matches;
    }

    public List<Match> getMatchCollectionByNickname(String nickname) throws IOException, InterruptedException {
        return getMatchCollectionByNickname(nickname, 4);
    }

    /**
     * Returns Match class object that contains details about summoner's match from Riot's API
     * Riot's path: /lol/match/v5/matches/{matchId}
     * @param id Id of match played by user
     *
     * @return Match class' objects that contains details about summoner's match from Riot's API
     */
    public Match getMatchById(String id) throws IOException, InterruptedException {
        HttpResponseWrapper<Match> matchHttpResponseWrapper =
                riotHttpClient.get("https://europe.api.riotgames.com/lol/match/v5/matches/" + id,
                        Match.class);
        if (!matchHttpResponseWrapper.isSuccess())
            throw new IllegalStateException("Data from Riot's API cannot be retried");
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

    private List<MatchDetailsDto> getPlayerMatchDetailsList(String nickname) throws IOException, InterruptedException {
        var matches = getMatchCollectionByNickname(nickname);
        List<MatchDetailsDto> matchDetailList = new ArrayList<>();
        for (var match : matches){
            matchDetailList.add(getPlayerMatchDetails(match, nickname));
        }
        return matchDetailList;
    }

    private MatchDetailsDto getPlayerMatchDetails(Match match, String nickname) throws IOException, InterruptedException {
        ParticipantsItem participant = match.getInfo().getParticipants().stream()
                .filter(e -> e.getSummonerName().equals(nickname))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no such a summoner"));
        int matchId = participant.getTeamId();
        List<PlayerGameDto> allies =
                getTeamMembers(match, matchId, true);
        List<PlayerGameDto> enemies =
                getTeamMembers(match, matchId, false);
        //TODO: championIcon have to be downloaded from file
        // participation in kill is not fully done yet
        return new MatchDetailsDto.Builder()
                .championName(participant.getChampionName())
                .timeDurationOfMatch(match.getInfo().getGameDuration())
                .championIconUrl("")
                .summoner1Id(participant.getSummoner1Id())
                .summoner2Id(participant.getItem2())
                .kills(participant.getKills())
                .deaths(participant.getDeaths())
                .assists(participant.getAssists())
                .champLvl(participant.getChampLevel())
                .killedMinions(participant.getTotalMinionsKilled())
                .pInKill(0.0)
                .allies(allies)
                .enemies(enemies)
                .isWin(participant.isWin())
                .build();
    }

    private List<PlayerGameDto> getTeamMembers(Match match, int matchId, boolean isAlly){
        if (isAlly)
            return match.getInfo().getParticipants().stream()
                    .filter(e -> e.getTeamId() == matchId)
                    .map(PlayerGameDto::new)
                    .collect(Collectors.toList());
        return match.getInfo().getParticipants().stream()
                .filter(e -> e.getTeamId() != matchId)
                .map(PlayerGameDto::new)
                .collect(Collectors.toList());
    }

    public List<PreferedRoleDto> getSummonersPreferredRole(String nickname) throws IOException, InterruptedException {
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
        Map<String, List<ParticipantsItem>> byRole = participants.stream()
                .collect(Collectors.groupingBy(ParticipantsItem::getRole));
        int playedMatches = participants.size();
        List<PreferedRoleDto> preferredRoleDots = new ArrayList<>();

        for (var roleName : byRole.keySet()){
            double victories = byRole.get(roleName).stream()
                    .filter(ParticipantsItem::isWin)
                    .count();
            double defeats = byRole.get(roleName).stream()
                    .filter(e -> !e.isWin())
                    .count();
            double winRatio = victories / (defeats + victories);
            int numberOfPickedRole = byRole.get(roleName).size();
            double pickRatio = (double) numberOfPickedRole / (double) playedMatches;
            preferredRoleDots.add(new PreferedRoleDto(pickRatio, winRatio, roleName));
        }
        return preferredRoleDots;
    }
}
