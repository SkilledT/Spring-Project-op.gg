package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.*;
import leagueoflegendsproject.v2.Repositories.TeamObjectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamObjectiveService {
    private TeamObjectiveRepository teamObjectiveRepository;

    private TeamObjective createTeamObjective(Baron baron, Inhibitor inhibitor,
                                              Dragon dragon, RiftHerald riftHerald, ObjectiveChampion objectiveChampion,
                                              Tower tower) {
        var teamObjective = new TeamObjective()
                .setBaron(baron)
                .setInhibitor(inhibitor)
                .setDragon(dragon)
                .setRiftHerald(riftHerald)
                .setChampion(objectiveChampion)
                .setTower(tower);

        baron.setTeamObjective(teamObjective);
        inhibitor.setTeamObjective(teamObjective);
        dragon.setTeamObjective(teamObjective);
        riftHerald.setTeamObjective(teamObjective);
        objectiveChampion.setTeamObjective(teamObjective);
        tower.setTeamObjective(teamObjective);
        return teamObjective;
    }

    public TeamObjective createOrGetTeamObjective(Baron baron, Inhibitor inhibitor,
                                                  Dragon dragon, RiftHerald riftHerald, ObjectiveChampion objectiveChampion,
                                                  Tower tower, Integer externalTeamId) {
        if (externalTeamId == null) {
            return createTeamObjective(baron, inhibitor, dragon,
                    riftHerald, objectiveChampion, tower);
        }
        var dbTeamObjective = teamObjectiveRepository.findByTeamExternalId(externalTeamId);
        return dbTeamObjective.orElseGet(() -> teamObjectiveRepository.save(
                createTeamObjective(baron, inhibitor, dragon, riftHerald, objectiveChampion, tower))
        );
    }
}
