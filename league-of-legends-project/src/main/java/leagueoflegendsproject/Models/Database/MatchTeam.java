package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.MatchTeamKey;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "match_team")
public class MatchTeam {

    @EmbeddedId
    private MatchTeamKey key = new MatchTeamKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    @MapsId(value = "matchId")
    private Match match;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @MapsId(value = "teamId")
    private Team team;

    @OneToMany(mappedBy = "matchTeam")
    private Set<TeamObjective> teamObjectiveSet = new HashSet<>();

    @OneToMany(mappedBy = "matchTeam")
    private Set<Ban> banSet = new HashSet<>();

    public MatchTeam() {
    }

    public MatchTeam(MatchTeamKey key, Match match, Team team, Set<TeamObjective> teamObjectiveSet, Set<Ban> banSet) {
        this.key = key;
        this.match = match;
        this.team = team;
        this.teamObjectiveSet = teamObjectiveSet;
        this.banSet = banSet;
    }

    public MatchTeam(Match match, Team team) {
        this.match = match;
        this.team = team;
    }

    public MatchTeamKey getKey() {
        return key;
    }

    public void setKey(MatchTeamKey key) {
        this.key = key;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<TeamObjective> getTeamObjectiveSet() {
        return teamObjectiveSet;
    }

    public void setTeamObjectiveSet(Set<TeamObjective> teamObjectiveSet) {
        this.teamObjectiveSet = teamObjectiveSet;
    }

    public Set<Ban> getBanSet() {
        return banSet;
    }

    public void setBanSet(Set<Ban> banSet) {
        this.banSet = banSet;
    }
}
