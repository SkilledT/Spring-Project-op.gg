package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.*;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.*;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.BanKey;
import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.Keys.MatchTeamKey;
import leagueoflegendsproject.Models.Database.Keys.TeamObjectiveKey;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import leagueoflegendsproject.Strategies.RoleStrategies.PerformanceStrategyFactory;
import leagueoflegendsproject.Utils.MatchParticipantUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static leagueoflegendsproject.Helpers.MatchUtils.checkIfMatchIsSoloQ;

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

    @Transactional
    public void AddMatchToDb(Match match) {
        var repoMatch = matchRepository.findById(match.getMetadata().getMatchId()).orElse(null);
        if (repoMatch != null || !checkIfMatchIsSoloQ(match))
            return;
        leagueoflegendsproject.Models.Database.Match dbMatch =
                matchRepository.findById(match.getMetadata().getMatchId())
                        .orElseGet(() -> new leagueoflegendsproject.Models.Database.Match(match));

        var matchTeams = match.getInfo().getParticipants().stream()
                .map(participant -> proceedMatchParticipants(participant, dbMatch))
                .collect(Collectors.toSet());

        proceedMatchTeams(matchTeams, match);
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
                    double pInKill = MatchParticipantUtils.getKillParticipation(mp);
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

    private MatchTeam proceedMatchParticipants(ParticipantsItem participant, leagueoflegendsproject.Models.Database.Match dbMatch) {
        Champion dbChampion = championRepository
                .findById(participant.getChampionId())
                .orElseGet(() -> new Champion(participant));
        dbChampion.update(participant);
        Summoner summoner = summonerRepository
                .findById(participant.getSummonerId())
                .orElseGet(() -> httpSummonerService.fetchSummonerAndSaveToDbHTTP(participant.getSummonerName()));

        MatchParticipant matchParticipant =
                matchParticipantRepository.findById(new MatchParticipantKey(dbMatch.getMatchId(), summoner.getSummonerId()))
                        .orElseGet(() -> new MatchParticipant(participant));
        MatchParticipant dbMatchParticipant = saveMatchParticipant(summoner, dbMatch, matchParticipant, dbChampion, participant.getTeamId());

        List<Integer> items =
                List.of(participant.getItem0(), participant.getItem1(), participant.getItem2(), participant.getItem3(), participant.getItem4(), participant.getItem5());

        items.forEach(participantItem -> saveItem(dbMatchParticipant, participantItem));

        participant.getPerks().getStyles()
                .forEach(stylesItem -> {
                    stylesItem.getSelections().forEach(selectionsItem -> {
                        savePerk(dbMatchParticipant, selectionsItem.getPerk());
                    });
                });

        return saveMatchTeam(dbMatch, participant.getTeamId());
    }

    private void proceedMatchTeams(Set<MatchTeam> matchTeams, Match match) {
        matchTeams.forEach(matchTeam -> match.getInfo().getTeams().stream()
                .filter(e -> e.getTeamId() == matchTeam.getTeam().getId())
                .forEach(teamsItem -> {
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
                }));

        matchTeams.forEach(matchTeam ->
                match.getInfo().getTeams().stream()
                        .filter(e -> e.getTeamId() == matchTeam.getTeam().getId())
                        .forEach(teamsItem ->
                                teamsItem.getBans().forEach(bansItem -> {
                                    int pickTurn = bansItem.getPickTurn();
                                    saveBan(matchTeam, bansItem.getChampionId(), pickTurn);
                                }))
        );
    }

    private Objective createObjective(String objectiveName) {
        return objectiveRepository
                .findById(objectiveName)
                .orElse(new Objective(objectiveName));
    }

    private MatchParticipant saveMatchParticipant(Summoner summoner,
                                                  leagueoflegendsproject.Models.Database.Match match,
                                                  MatchParticipant matchParticipant,
                                                  Champion champion,
                                                  Integer teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseGet(() -> new Team(teamId));
        matchParticipant.setMatch(match);
        matchParticipant.setSummoner(summoner);
        matchParticipant.setChampion(champion);
        matchParticipant.setTeam(team);
        return matchParticipantRepository.save(matchParticipant);
    }

    private void saveItem(MatchParticipant matchParticipant, Integer itemId) {
        ParticipantItems participantItems = new ParticipantItems();
        Item item = itemRepository.findById(itemId)
                .orElseGet(() -> new Item(itemId));
        participantItems.setItem(item);
        participantItems.setMatchParticipant(matchParticipant);
        participantItemsRepository.save(participantItems);
    }

    private void savePerk(MatchParticipant matchParticipant, Integer perkId) throws IllegalStateException {
        MatchParticipantPerk matchParticipantPerk = new MatchParticipantPerk();
        Optional<Perk> perk = perkRepository.findById(perkId);
        perk.ifPresent(perkLambda -> {
            matchParticipantPerk.setMatchParticipant(matchParticipant);
            matchParticipantPerk.setPerk(perkLambda);
            matchParticipantPerkRepository.save(matchParticipantPerk);
        });
    }

    private void saveBan(MatchTeam matchTeam, Integer championId, int pickTurn) {
        Ban ban = banRepository
                .findById(new BanKey(new MatchTeamKey(matchTeam.getMatch().getMatchId(), matchTeam.getTeam().getId()), championId))
                .orElse(new Ban());
        Champion champion = championRepository
                .findById(championId)
                .orElseGet(() -> new Champion(championId, String.valueOf(championId)));
        MatchTeam dbMatchTeam = matchTeamRepository.findById(new MatchTeamKey(matchTeam.getMatch().getMatchId(), matchTeam.getTeam().getId()))
                .orElseGet(() -> new MatchTeam(matchTeam.getMatch(), matchTeam.getTeam()));
        ban.setMatchTeam(dbMatchTeam);
        ban.setChampion(champion);
        ban.setPickTurn(pickTurn);
        banRepository.save(ban);
    }

    private TeamObjective saveTeamObjective(MatchTeam matchTeam, Objective objective, boolean first, int kills) {
        MatchTeam dbMatchTeam = matchTeamRepository
                .findById(new MatchTeamKey(matchTeam.getMatch().getMatchId(), matchTeam.getTeam().getId()))
                .orElseGet(() -> new MatchTeam(matchTeam.getMatch(), matchTeam.getTeam()));
        Objective dbObjective = objectiveRepository.findById(objective.getName())
                .orElse(objective);
        TeamObjective teamObjective = teamObjectiveRepository
                .findById(new TeamObjectiveKey(objective.getName(), new MatchTeamKey(dbMatchTeam.getMatch().getMatchId(), dbMatchTeam.getTeam().getId())))
                .orElse(new TeamObjective());
        teamObjective.setMatchTeam(dbMatchTeam);
        teamObjective.setObjective(dbObjective);
        teamObjective.setKills(kills);
        teamObjective.setFirst(first);
        return teamObjectiveRepository.save(teamObjective);
    }

    private MatchTeam saveMatchTeam(leagueoflegendsproject.Models.Database.Match match, Integer teamId) {
        MatchTeam matchTeam = new MatchTeam();
        Team team = teamRepository
                .findById(teamId)
                .orElseGet(() -> new Team(teamId));
        matchTeam.setTeam(team);
        matchTeam.setMatch(match);
        return matchTeam;
    }
}
