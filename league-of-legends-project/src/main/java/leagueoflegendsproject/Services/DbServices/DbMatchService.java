package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.*;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.*;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.*;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import leagueoflegendsproject.Strategies.RoleStrategies.PerformanceStrategyFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static leagueoflegendsproject.Helpers.MatchUtils.isMatchSoloQ;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DbMatchService {

    private final SummonerRepository summonerRepository;
    private final ItemRepository itemRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final ParticipantItemsRepository participantItemsRepository;
    private final MatchParticipantRepository matchParticipantRepository;
    private final BanRepository banRepository;
    private final MatchTeamRepository matchTeamRepository;
    private final ChampionRepository championRepository;
    private final TeamObjectiveRepository teamObjectiveRepository;
    private final ObjectiveRepository objectiveRepository;
    private final MatchParticipantPerkRepository matchParticipantPerkRepository;
    private final PerkRepository perkRepository;
    private final HttpSummonerService httpSummonerService;
    private final PerformanceStrategyFactory performanceStrategyFactory;

    public DbMatchService(final SummonerRepository summonerRepository,
                          final ItemRepository itemRepository,
                          final TeamRepository teamRepository,
                          final MatchRepository matchRepository,
                          final BanRepository banRepository,
                          final TeamObjectiveRepository teamObjectiveRepository,
                          final MatchTeamRepository matchTeamRepository,
                          final ParticipantItemsRepository participantItemsRepository,
                          final MatchParticipantRepository matchParticipantRepository,
                          final ObjectiveRepository objectiveRepository,
                          final PerkRepository perkRepository,
                          final MatchParticipantPerkRepository matchParticipantPerkRepository,
                          final HttpSummonerService httpSummonerService,
                          final ChampionRepository championRepository, PerformanceStrategyFactory performanceStrategyFactory) {
        this.summonerRepository = summonerRepository;
        this.itemRepository = itemRepository;
        this.teamRepository = teamRepository;
        this.participantItemsRepository = participantItemsRepository;
        this.matchParticipantRepository = matchParticipantRepository;
        this.championRepository = championRepository;
        this.objectiveRepository = objectiveRepository;
        this.matchRepository = matchRepository;
        this.teamObjectiveRepository = teamObjectiveRepository;
        this.matchTeamRepository = matchTeamRepository;
        this.banRepository = banRepository;
        this.perkRepository = perkRepository;
        this.matchParticipantPerkRepository = matchParticipantPerkRepository;
        this.httpSummonerService = httpSummonerService;
        this.performanceStrategyFactory = performanceStrategyFactory;
    }

    @Cacheable(cacheNames = "Players_Match_Collection")
    public List<MatchDetailsDto> getMatchesByNickname(String nickname) {
        List<MatchParticipant> matchParticipant = matchParticipantRepository.findBySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(nickname)
                .stream()
                .filter(e -> e.getMatch().getMatchParticipantSet().stream().filter(x -> x.getIndividualPosition().equals(Constants.MatchParticipantConstants.IndividualPosition.Invalid)).findFirst().orElse(null) == null)
                .collect(Collectors.toList());
        return matchParticipant.stream()
                .map(mp -> {
                    Set<MatchParticipant> alliesMatchParticipantSet = new HashSet<>();
                    Set<MatchParticipant> enemiesMatchParticipantSet = new HashSet<>();
                    Integer teamId = mp.getTeam().getId();
                    for (MatchParticipant innerMp : mp.getMatch().getMatchParticipantSet()) {
                        if (innerMp.getTeam().getId().equals(teamId))
                            alliesMatchParticipantSet.add(innerMp);
                        else
                            enemiesMatchParticipantSet.add(innerMp);
                    }
                    Set<ItemMatchDto> itemMatchDtos = mp.getParticipantItemsSet().stream()
                            .map(item -> new ItemMatchDto(String.valueOf(item.getItem().getId())))
                            .collect(Collectors.toSet());
                    Set<PlayerGameDto> allies = alliesMatchParticipantSet.stream()
                            .map(e -> new PlayerGameDto(e.getChampionName(), e.getSummoner().getSummonerNickname(), e.getSummoner().getSummonerId()))
                            .collect(Collectors.toSet());
                    Set<PlayerGameDto> enemies = enemiesMatchParticipantSet.stream()
                            .map(e -> new PlayerGameDto(e.getChampionName(), e.getSummoner().getSummonerNickname(), e.getSummoner().getSummonerId()))
                            .collect(Collectors.toSet());
                    double pInKill = mp.getKillParticipation();
                    double performanceScore = performanceStrategyFactory.findStrategyByIndividualPosition(mp.getIndividualPosition()).countPerformanceRate(mp);
                    return new MatchDetailsDto.Builder()
                            .timeDurationOfMatch(mp.getMatch().getGameDuration())
                            .assists(mp.getAssists())
                            .kills(mp.getKills())
                            .deaths(mp.getDeaths())
                            .isWin(mp.getWin())
                            .killedMinions(mp.getTotalMinionsKilled())
                            .champLvl(mp.getChampLvl())
                            .championName(mp.getChampionName())
                            .position(mp.getIndividualPosition())
                            .summoner1Id(mp.getSummoner1Id())
                            .summoner2Id(mp.getSummoner2Id())
                            .controlWardsPurchased(mp.getVisionWardsBoughtInGame())
                            .list(itemMatchDtos)
                            .allies(allies)
                            .enemies(enemies)
                            .pInKill(pInKill)
                            .withGameCreation(mp.getMatch().getGameCreation())
                            .withPerformanceScore(performanceScore)
                            .build();
                }).sorted((match1, match2) -> match2.getGameCreation().compareTo(match1.getGameCreation()))
                .collect(Collectors.toList());
    }

    public Set<PlayersChampionStatsDto> getChampionStatsByNickname(String nickname) {
        List<MatchParticipant> matchParticipant = matchParticipantRepository.findBySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(nickname);
        Map<Champion, List<MatchParticipant>> championListMap =
                matchParticipant.stream().collect(Collectors.groupingBy(MatchParticipant::getChampion));
        return championListMap.keySet().stream().map(champion -> {
            List<MatchParticipant> matchParticipants = championListMap.get(champion);
            long victories = matchParticipants.stream().filter(MatchParticipant::getWin).count();
            double winRatio = (double) victories / (double) matchParticipants.size();
            String champName = champion.getName();
            double avgCS = matchParticipants.stream().mapToDouble(MatchParticipant::getTotalMinionsKilled).average().orElse(Double.NaN);
            int playedMatches = matchParticipants.size();
            double avgKills = matchParticipants.stream().mapToDouble(MatchParticipant::getKills).average().orElse(Double.NaN);
            double avgDeaths = matchParticipants.stream().mapToDouble(MatchParticipant::getDeaths).average().orElse(Double.NaN);
            double avgAssists = matchParticipants.stream().mapToDouble(MatchParticipant::getAssists).average().orElse(Double.NaN);
            double KDA = (avgKills + avgAssists) / avgDeaths;
            String champUrl = champion.getIconUrl();
            return new PlayersChampionStatsDto(winRatio, champName, avgCS, playedMatches, avgKills, avgDeaths, avgAssists, KDA, champUrl);
        }).collect(Collectors.toSet());
    }

    public List<PreferedRoleDto> getPreferredRole(String nickname) {
        List<MatchParticipant> matchParticipant = matchParticipantRepository.findBySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(nickname);
        Map<Constants.MatchParticipantConstants.IndividualPosition, Long> roles =
                matchParticipant.stream()
                        .filter(e -> !e.getIndividualPosition().equals(Constants.MatchParticipantConstants.IndividualPosition.Invalid))
                        .collect(Collectors.groupingBy(MatchParticipant::getIndividualPosition, Collectors.counting()));
        long playedGames = matchParticipant.size();
        return roles.keySet().stream().map(individualPosition -> {
                    long picks = roles.get(individualPosition);
                    double pickRatio = (double) picks / (double) playedGames;
                    long wins = matchParticipant.stream()
                            .filter(MatchParticipant::getWin)
                            .filter(e -> e.getIndividualPosition().equals(individualPosition))
                            .count();
                    long playedOnLane = matchParticipant.stream()
                            .filter(e -> e.getIndividualPosition().equals(individualPosition))
                            .count();
                    double winRatio = (double) wins / (playedOnLane == 0 ? 1 : (double) playedOnLane);
                    return new PreferedRoleDto(pickRatio, winRatio, individualPosition);
                }).sorted(Comparator.comparingDouble(PreferedRoleDto::getPickRatio).reversed())
                .collect(Collectors.toList());
    }

    private Map<Integer, Team> getAvailableTeams(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match apiMatch) {
        Set<Integer> teamIds = apiMatch.getInfo().getTeams().stream().map(TeamsItem::getTeamId).collect(Collectors.toSet());
        List<Team> availableTeams = teamRepository.findAllById(teamIds);
        Set<Integer> foundIds = availableTeams.stream().map(Team::getId).collect(Collectors.toSet());
        teamIds.stream().filter(teamId -> !foundIds.contains(teamId)).forEach(id -> availableTeams.add(new Team(id)));
        return availableTeams.stream().collect(Collectors.toMap(Team::getId, x -> x));
    }

    private Map<Integer, Champion> getAvailableChampions(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match apiMatch) {
        Set<Integer> championIds = apiMatch.getInfo().getParticipants().stream().map(ParticipantsItem::getChampionId).collect(Collectors.toSet());
        for(var banItem : apiMatch.getInfo().getTeams().stream().map(TeamsItem::getBans).collect(Collectors.toSet())) {
            banItem.stream().map(BansItem::getChampionId).forEach(championIds::add);
        }
        List<ParticipantsItem> participantsItems = apiMatch.getInfo().getParticipants();
        List<Champion> availableChampions = championRepository.findAllById(championIds);
        Set<Integer> foundIds = availableChampions.stream().map(Champion::getId).collect(Collectors.toSet());
        participantsItems.stream().filter(pItem -> !foundIds.contains(pItem.getChampionId())).forEach(pItem -> availableChampions.add(new Champion(pItem.getChampionId(), pItem.getChampionName())));
        return availableChampions.stream().collect(Collectors.toUnmodifiableMap(Champion::getId, x -> x));
    }

    private Map<Integer, Item> getAvailableItems(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match apiMatch) {
        Set<Integer> itemIds = new HashSet<>();
        apiMatch.getInfo().getParticipants().forEach(participant -> {
            List<Integer> ids = List.of(participant.getItem0(), participant.getItem1(), participant.getItem2(), participant.getItem3(), participant.getItem4(), participant.getItem5());
            itemIds.addAll(ids);
        });
        List<Item> availableItems = itemRepository.findAllById(itemIds);
        Set<Integer> foundIds = availableItems.stream().map(Item::getId).collect(Collectors.toSet());
        itemIds.stream().filter(itemId -> !foundIds.contains(itemId)).forEach(itemId -> availableItems.add(new Item(itemId)));

        return availableItems.stream().collect(Collectors.toMap(Item::getId, x -> x));
    }

    private Map<Integer, Perk> getAvailablePerks(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match apiMatch) {
        Set<Integer> perkIds = new HashSet<>();
        var participants = apiMatch.getInfo().getParticipants();
        for (var p : participants) {
            var sItemPerkIds = extractSelectionItem(p).stream().map(SelectionsItem::getPerk).collect(Collectors.toSet());
            perkIds.addAll(sItemPerkIds);
        }
        List<Perk> availablePerks = perkRepository.findAllById(perkIds);
        return availablePerks.stream().collect(Collectors.toMap(Perk::getId, x -> x));
    }

    @Transactional()
    public void AddMatchToDb(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match apiMatch) {
        var repoMatch = matchRepository.findById(apiMatch.getMetadata().getMatchId()).orElse(null);
        if (repoMatch != null || !isMatchSoloQ(apiMatch))
            return;
        leagueoflegendsproject.Models.Database.Match match = new leagueoflegendsproject.Models.Database.Match(apiMatch);
        Map<Integer, Team> availableTeams = getAvailableTeams(apiMatch);
        Map<Integer, Item> availableItems = getAvailableItems(apiMatch);
        Map<Integer, Perk> availablePerks = getAvailablePerks(apiMatch);
        Map<Integer, Champion> availableChampions = getAvailableChampions(apiMatch);

        apiMatch.getInfo().getParticipants().stream()
                .collect(Collectors.toUnmodifiableList())
                .forEach(participant -> handleMatchParticipantPersist(availableTeams, match, participant, availableItems, availablePerks, availableChampions));

        Set<MatchTeam> matchTeamSet = availableTeams.keySet().stream()
                .map(availableTeams::get)
                .map(team -> createMatchTeam(team, match))
                .collect(Collectors.toSet());
        handleMatchTeamPersist(matchTeamSet, apiMatch, availableChampions, match);
        matchTeamRepository.saveAll(matchTeamSet);
    }

    private void handleMatchParticipantPersist(Map<Integer, Team> availableTeams, leagueoflegendsproject.Models.Database.Match match, ParticipantsItem participant, Map<Integer, Item> availableItems, Map<Integer, Perk> availablePerks, Map<Integer, Champion> availableChampions) {
        MatchParticipant matchParticipant = new MatchParticipant(participant);
        setMatchParticipant(participant, match, availableTeams, matchParticipant, availableItems, availablePerks, availableChampions);
    }

    private void handleMatchTeamPersist(Set<MatchTeam> matchTeamSet, leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match apiMatch, Map<Integer, Champion> availableChampions, Match match) {
        setBan(matchTeamSet, apiMatch, availableChampions);
        for (MatchTeam matchTeam : matchTeamSet) {
            var teamItems = apiMatch.getInfo().getTeams().stream()
                    .filter(e -> e.getTeamId() == matchTeam.getTeam().getId())
                    .collect(Collectors.toSet());
            for (TeamsItem tItem : teamItems) {
                setMatchTeamObjective(matchTeam, tItem);
            }
            match.addMatchTeamChild(matchTeam);
        }
    }

    private void setMatchParticipant(ParticipantsItem participant, leagueoflegendsproject.Models.Database.Match match, Map<Integer, Team> availableTeams, MatchParticipant matchParticipant, Map<Integer, Item> availableItems, Map<Integer, Perk> availablePerks, Map<Integer, Champion> availableChampions) {
        Team team = availableTeams.get(participant.getTeamId());
        match.addMatchParticipantChild(matchParticipant);
        team.addMatchParticipantChild(matchParticipant);
        Champion champion = availableChampions.get(participant.getChampionId());
        Summoner summoner = summonerRepository
                .findById(participant.getSummonerId())
                .orElseGet(() -> httpSummonerService.fetchSummonerAndSaveToDbHTTP(participant.getSummonerName()));
        matchParticipant.setSummoner(summoner);
        matchParticipant.setChampion(champion);

        setMatchParticipantItems(participant, matchParticipant, availableItems);
        setMatchParticipantPerks(participant, matchParticipant, availablePerks);
    }

    private void setMatchParticipantItems(ParticipantsItem participant, MatchParticipant matchParticipant, Map<Integer, Item> availableItems) {
        List<Integer> itemIds = List.of(participant.getItem0(), participant.getItem1(), participant.getItem2(), participant.getItem3(), participant.getItem4(), participant.getItem5());
        for (int i = 0; i < itemIds.size(); i++) {
            var itemId = itemIds.get(i);
            if (availableItems.containsKey(itemId)) {
                Item item = availableItems.get(itemId);
                ParticipantItems participantItems = new ParticipantItems();
                participantItems.setItem(item);
                participantItems.setMatchParticipant(matchParticipant);
                participantItems.setPosition(i);
                matchParticipant.addParticipantItemChild(participantItems);
            }
        }
    }

    private void setMatchParticipantPerks(ParticipantsItem participant, MatchParticipant matchParticipant, Map<Integer, Perk> availablePerks) {
        for (StylesItem styleItem : participant.getPerks().getStyles()) {
            for (SelectionsItem selectionsItem : styleItem.getSelections()) {
                MatchParticipantPerk matchParticipantPerk = new MatchParticipantPerk();
                if (availablePerks.containsKey(selectionsItem.getPerk())) {
                    Perk perk = availablePerks.get(selectionsItem.getPerk());
                    matchParticipantPerk.setMatchParticipant(matchParticipant);
                    matchParticipantPerk.setPerk(perk);
                    matchParticipant.addMatchParticipantPerkChild(matchParticipantPerk);
                }
            }
        }
    }

    private Set<SelectionsItem> extractSelectionItem(ParticipantsItem participant) {
        Set<SelectionsItem> selectionsItemSet = new HashSet<>();
        for (StylesItem styleItem : participant.getPerks().getStyles()) {
            selectionsItemSet.addAll(styleItem.getSelections());
        }
        return selectionsItemSet;
    }

    private MatchTeam createMatchTeam(Team team, leagueoflegendsproject.Models.Database.Match match) {
        MatchTeam matchTeam = new MatchTeam();
        matchTeam.setTeam(team);
        matchTeam.setMatch(match);
        return matchTeam;
    }

    private void setBan(Set<MatchTeam> matchTeamList, leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match, Map<Integer, Champion> availableChampions) {
        for (MatchTeam matchTeam : matchTeamList) {
            match.getInfo().getTeams().stream().filter(e -> e.getTeamId() == matchTeam.getTeam().getId()).forEach(teamsItem -> {
                teamsItem.getBans().stream().forEach(banItem -> {
                    int pickTurn = banItem.getPickTurn();
                    int championId = banItem.getChampionId();
                    if (availableChampions.containsKey(championId)) {
                        Champion champion = availableChampions.get(championId);
                        Ban ban = new Ban();
                        ban.setMatchTeam(matchTeam);
                        ban.setChampion(champion);
                        ban.setPickTurn(pickTurn);
                        matchTeam.addBanChild(ban);
                    }
                });
            });
        }
    }

    private void setMatchTeamObjective(MatchTeam matchTeam, TeamsItem teamsItem) {
        var objectives = teamsItem.getObjectives();
        var baron = saveTeamObjective(matchTeam,
                createObjective(objectives.getBaron().getClass().getSimpleName()),
                objectives.getBaron().isFirst(),
                objectives.getBaron().getKills());
        var inhibitor = saveTeamObjective(matchTeam,
                createObjective(objectives.getInhibitor().getClass().getSimpleName()),
                objectives.getInhibitor().isFirst(),
                objectives.getInhibitor().getKills());
        var dragon = saveTeamObjective(matchTeam,
                createObjective(objectives.getDragon().getClass().getSimpleName()),
                objectives.getDragon().isFirst(),
                objectives.getDragon().getKills());
        var riftHerald = saveTeamObjective(matchTeam,
                createObjective(objectives.getRiftHerald().getClass().getSimpleName()),
                objectives.getRiftHerald().isFirst(),
                objectives.getRiftHerald().getKills());
        var champion = saveTeamObjective(matchTeam,
                createObjective(objectives.getChampion().getClass().getSimpleName()),
                objectives.getChampion().isFirst(),
                objectives.getChampion().getKills());
        var tower = saveTeamObjective(matchTeam,
                createObjective(objectives.getTower().getClass().getSimpleName()),
                objectives.getTower().isFirst(),
                objectives.getTower().getKills());
        matchTeam.addTeamObjectChild(baron);
        matchTeam.addTeamObjectChild(inhibitor);
        matchTeam.addTeamObjectChild(dragon);
        matchTeam.addTeamObjectChild(riftHerald);
        matchTeam.addTeamObjectChild(champion);
        matchTeam.addTeamObjectChild(tower);
    }

    private TeamObjective saveTeamObjective(MatchTeam matchTeam, Objective objective, boolean first, int kills) {
        Objective dbObjective = objectiveRepository.findById(objective.getName()).orElse(objective);
        TeamObjective teamObjective = new TeamObjective();
        teamObjective.setObjective(dbObjective);
        teamObjective.setMatchTeam(matchTeam);
        teamObjective.setKills(kills);
        teamObjective.setFirst(first);

        matchTeam.addTeamObjectChild(teamObjective);
        return teamObjective;
    }

    private Objective createObjective(String objectiveName) {
        return objectiveRepository
                .findById(objectiveName)
                .orElse(new Objective(objectiveName));
    }
}
