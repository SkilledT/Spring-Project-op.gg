package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.*;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
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
    Match getMatchById(String id) throws IOException, InterruptedException {
        HttpResponseWrapper<Match> matchHttpResponseWrapper =
                riotHttpClient.get("https://europe.api.riotgames.com/lol/match/v5/matches/" + id,
                        Match.class);
        if (!matchHttpResponseWrapper.isSuccess())
            throw new IllegalStateException("Data from Riot's API cannot be retried");
        return matchHttpResponseWrapper.getResponse();
    }

    public List<PlayersChampionStatsDto> getChampionStatsByNickname(String nickname) throws IOException, InterruptedException {
        Map<String, List<ParticipantsItem>> byChampName = getMapGroupedBy(nickname, ParticipantsItem::getChampionName);

        List<PlayersChampionStatsDto> playersChampionStatsDtos = new ArrayList<>();
        for (var key : byChampName.keySet()){
            var values = byChampName.get(key);
            double winRatio = countWinRatio(values);
            String champName = key;
            double CS = getAverageValue(values, ParticipantsItem::getTotalMinionsKilled);
            double avgKills = getAverageValue(values, ParticipantsItem::getKills);
            double avgDeaths = getAverageValue(values, ParticipantsItem::getDeaths);
            double avgAssists = getAverageValue(values, ParticipantsItem::getAssists);
            int playedMatches = values.size();
            PlayersChampionStatsDto playersChampionStatsDto =
                    new PlayersChampionStatsDto(winRatio, champName, CS, playedMatches, avgKills, avgDeaths, avgAssists);
            playersChampionStatsDtos.add(playersChampionStatsDto);
        }
        return playersChampionStatsDtos;
    }

    private <T> double getAverageValue(Collection<T> collection, ToDoubleFunction<T> toDoubleFunction){
        return collection.stream().mapToDouble(toDoubleFunction)
                .average()
                .orElse(Double.NaN);
    }

    private double countWinRatio(List<ParticipantsItem> participantsItems){
        double victories = getNumberOfElements(participantsItems, ParticipantsItem::isWin);
        long playedGames = participantsItems.size();
        return (double)victories/(double)playedGames;
    }

    public List<MatchDetailsDto> getPlayerMatchDetailsList(String nickname) throws IOException, InterruptedException {
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
        List<ItemMatchDto> itemList = Arrays.asList(
                new ItemMatchDto(participant.getItem1()),
                new ItemMatchDto(participant.getItem2()),
                new ItemMatchDto(participant.getItem3()),
                new ItemMatchDto(participant.getItem4()),
                new ItemMatchDto(participant.getItem5()),
                new ItemMatchDto(participant.getItem0())
                );
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
                .position(participant.getRole())
                .list(itemList)
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
        Map<String, List<ParticipantsItem>> byRole = getMapGroupedBy(nickname, ParticipantsItem::getRole);
        int playedMatches = byRole.keySet().stream().mapToInt(key -> byRole.get(key).size()).sum();
        List<PreferedRoleDto> preferredRoleDots = new ArrayList<>();

        for (String roleName : byRole.keySet()){
            var values = byRole.get(roleName);
            double victories = getNumberOfElements(values, ParticipantsItem::isWin);
            double defeats = getNumberOfElements(values, e -> !e.isWin());
            double winRatio = victories / (defeats + victories);
            int numberOfPickedRole = byRole.get(roleName).size();
            double pickRatio = (double) numberOfPickedRole / (double) playedMatches;
            preferredRoleDots.add(new PreferedRoleDto(pickRatio, winRatio, roleName));
        }
        return preferredRoleDots;
    }

    private <T> Map<T, List<ParticipantsItem>> getMapGroupedBy(String nickname, Function<ParticipantsItem, T> function) throws IOException, InterruptedException {
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
        Map<T, List<ParticipantsItem>> groupedBy = participants.stream()
                .collect(Collectors.groupingBy(function));
        return groupedBy;
    }

    private <T> double getNumberOfElements(Collection<T> collection, Predicate<T> filter){
        return collection.stream().filter(filter).count();
    }

}
