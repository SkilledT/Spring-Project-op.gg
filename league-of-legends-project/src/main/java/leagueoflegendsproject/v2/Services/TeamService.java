package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Match;
import leagueoflegendsproject.v2.Models.Team;
import leagueoflegendsproject.v2.Models.TeamObjective;
import leagueoflegendsproject.v2.Repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    private Team createTeam(Integer teamId, TeamObjective teamObjective, Match match) {
        var team =  new Team()
                .setExternalTeamId(teamId)
                .setTeamObjective(teamObjective)
                .setMatch(match);

        match.getTeams().add(team);
        teamObjective.setTeam(team);
        return team;
    }

    public Team createAndSaveTeam(Integer teamId, TeamObjective teamObjective, Match match) {
        var dbMatch = teamRepository
                .findByExternalMatchIdAndExternalTeamId(match.getExternalMatchId(), String.valueOf(teamId));
        return dbMatch.orElseGet(() -> teamRepository.save(createTeam(teamId, teamObjective, match)));
    }
}
