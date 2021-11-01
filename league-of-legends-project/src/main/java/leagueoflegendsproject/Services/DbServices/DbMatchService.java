package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.Models.Database.*;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DbMatchService {

    private final SummonerRepository summonerRepository;
    private final ItemRepository itemRepository;
    private final TeamRepository teamRepository;
    private final ChampionRepository championRepository;
    private final ObjectiveRepository objectiveRepository;

    public DbMatchService(final SummonerRepository summonerRepository,
                          final ItemRepository itemRepository,
                          final TeamRepository teamRepository,
                          final ObjectiveRepository objectiveRepository,
                          final ChampionRepository championRepository) {
        this.summonerRepository = summonerRepository;
        this.itemRepository = itemRepository;
        this.teamRepository = teamRepository;
        this.championRepository = championRepository;
        this.objectiveRepository = objectiveRepository;
    }

    public void AddMatchToDb(Match match){
        leagueoflegendsproject.Models.Database.Match dbMatch =
                new leagueoflegendsproject.Models.Database.Match(match);

        var participants = match.getInfo().getParticipants().stream().map(participant -> {
            MatchParticipant matchParticipant = new MatchParticipant(participant);
            List<Integer> items =
                    List.of(participant.getItem0(), participant.getItem1(), participant.getItem2(), participant.getItem3(), participant.getItem4(), participant.getItem5());
            Set<Item> dbItems = items.stream()
                    .map(e -> itemRepository.findById((long) e).orElse(new Item((long) e, e)))
                    .collect(Collectors.toSet());
            Set<ParticipantItems> dbParticipantItems = dbItems.stream().map(g -> {
                leagueoflegendsproject.Models.Database.ParticipantItems participantItems = new ParticipantItems();
                participantItems.setItem(g);
                participantItems.setMatchParticipant(matchParticipant);
                return participantItems;
            }).collect(Collectors.toSet());
            Champion dbChampion = championRepository
                    .findById((long) participant.getChampionId())
                    .orElse(new Champion(participant));
            Team team = teamRepository
                    .findById((long) participant.getTeamId())
                    .orElse(new Team((long)participant.getTeamId()));
            Summoner summoner = summonerRepository
                    .findById(participant.getSummonerId())
                    .orElse(new Summoner(participant));
            matchParticipant.setChampion(dbChampion);
            matchParticipant.setMatch(dbMatch);
            matchParticipant.setParticipantItemsSet(dbParticipantItems);
            matchParticipant.setSummoner(summoner);
            matchParticipant.setTeam(team);
            return matchParticipant;
        }).collect(Collectors.toSet());

        Set<MatchTeam> matchTeams = match.getInfo().getTeams().stream()
                .map(e -> {
                    MatchTeam matchTeam = new MatchTeam();
                    matchTeam.setMatch(dbMatch);
                    matchTeam.setTeam(teamRepository.findById((long)e.getTeamId()).orElse(new Team((long)e.getTeamId())));
                    var objectives = e.getObjectives();
                    var baron = createTeamObjective(objectives.getBaron().getClass().getSimpleName(),
                            objectives.getBaron().isFirst(),
                            objectives.getBaron().getKills());
                    var inhibitor = createTeamObjective(objectives.getInhibitor().getClass().getSimpleName(),
                            objectives.getInhibitor().isFirst(),
                            objectives.getInhibitor().getKills());
                    var dragon = createTeamObjective(objectives.getDragon().getClass().getSimpleName(),
                            objectives.getDragon().isFirst(),
                            objectives.getDragon().getKills());
                    var riftHerald = createTeamObjective(objectives.getRiftHerald().getClass().getSimpleName(),
                            objectives.getRiftHerald().isFirst(),
                            objectives.getRiftHerald().getKills());
                    var champion = createTeamObjective(objectives.getChampion().getClass().getSimpleName(),
                            objectives.getChampion().isFirst(),
                            objectives.getChampion().getKills());
                    var tower = createTeamObjective(objectives.getTower().getClass().getSimpleName(),
                            objectives.getTower().isFirst(),
                            objectives.getTower().getKills());
                    matchTeam.getTeamObjectiveSet()
                            .addAll(Set.of(baron, inhibitor, dragon, riftHerald, champion, tower));
                    return matchTeam;
                }).collect(Collectors.toSet());
        matchTeams.forEach(matchTeam -> {
            var bans = match.getInfo().getTeams().stream()
                    .filter(d -> d.getTeamId() == matchTeam.getTeam().getId())
                    .findFirst().orElseThrow().getBans();
            Set<Ban> dbBans = bans.stream().map(lolApiBans -> {
                Ban dbBan = new Ban();
                Champion dbChampion = championRepository.findById((long) lolApiBans.getChampionId())
                        .orElse(new Champion((long)lolApiBans.getChampionId(), "randomName"));
                dbBan.setChampion(dbChampion);
                dbBan.setMatchTeam(matchTeam);
                dbBan.setPickTurn(lolApiBans.getPickTurn());
                return dbBan;
            }).collect(Collectors.toSet());
            matchTeam.getBanSet().addAll(dbBans);
        });
        dbMatch.setMatchParticipantSet(participants);
        dbMatch.setMatchTeamSet(matchTeams);
    }

    public TeamObjective createTeamObjective(String objectiveName, boolean isFirst, int kills){
        var object = objectiveRepository
                .findById(objectiveName)
                .orElse(new Objective(objectiveName));
        TeamObjective objectTO = new TeamObjective();
        objectTO.setFirst(isFirst);
        objectTO.setKills(kills);
        objectTO.setObjective(object);
        return objectTO;
    }
}
