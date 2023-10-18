package leagueoflegendsproject.v2.Processors.Creators;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.TeamsItem;
import leagueoflegendsproject.v2.Models.*;
import leagueoflegendsproject.v2.Services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TeamProcessorCreator {
    private final TeamObjectiveService teamObjectiveService;
    private final BaronService baronService;
    private final InhibitorService inhibitorService;
    private final DragonService dragonService;
    private final RiftHeraldService riftHeraldService;
    private final TowerService towerService;
    private final TeamService teamService;

    public Map<Integer, Team> createTeamsAndGetByTeamExternalId(List<TeamsItem> teams, Match match) {
        Map<Integer, List<TeamsItem>> teamItemsByExternalId =
                teams.stream()
                        .collect(
                                Collectors.groupingBy(
                                        TeamsItem::getTeamId,
                                        Collectors.mapping(x -> x, Collectors.toList()))
                        );
        return teamItemsByExternalId.entrySet()
                .stream()
                .map(teamItem -> createTeam(teamItem, match))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Team::getExternalTeamId, team -> team));
    }

    private Team createTeam(Map.Entry<Integer, List<TeamsItem>> teamItem, Match match) {
        var commonItemOptional = teamItem.getValue().stream().findFirst();
        if (commonItemOptional.isEmpty()) {
            // TODO: RETRY MECHANISM, CANNOT GET OBJECT
            log.info("No common item for team with ID {}", teamItem.getKey());
            return null;
        }
        var commonItem = commonItemOptional.get();
        var externalId = commonItem.getTeamId();
        var teamObjective = teamObjectiveService.createOrGetTeamObjective(
                createBaron(commonItem.getObjectives().getBaron()),
                createInhibitor(commonItem.getObjectives().getInhibitor()),
                createDragon(commonItem.getObjectives().getDragon()),
                createRiftHerald(commonItem.getObjectives().getRiftHerald()),
                null,
                createTower(commonItem.getObjectives().getTower()),
                externalId
        );
        return teamService.createAndSaveTeam(externalId, teamObjective, match);
    }

    private Baron createBaron(leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Baron baron) {
        return baronService.saveBaron(baron.getKills(), baron.isFirst());
    }

    private Inhibitor createInhibitor(leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Inhibitor inhibitor) {
        return inhibitorService.saveInhibitor(inhibitor.getKills(), inhibitor.isFirst());
    }

    private Dragon createDragon(leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Dragon dragon) {
        return dragonService.saveDragon(dragon.getKills(), dragon.isFirst());
    }

    private RiftHerald createRiftHerald(leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.RiftHerald riftHerald) {
        return riftHeraldService.saveRiftHerald(riftHerald.getKills(), riftHerald.isFirst());
    }

    private Tower createTower(leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Tower tower) {
        return towerService.saveTower(tower.getKills(), tower.isFirst());
    }

}
