package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.MatchTeamKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_team")
public class MatchTeam {

    @EmbeddedId
    private MatchTeamKey key;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    @MapsId(value = "matchId")
    private Match match;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @MapsId(value = "teamId")
    private Team team;

    @OneToMany(mappedBy = "matchTeam")
    private Set<TeamObjective> teamObjectiveSet;

    @OneToMany(mappedBy = "matchTeam")
    private Set<Ban> banSet;

    public void setKey(MatchTeamKey key) {
        this.key = key;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeamObjectiveSet(Set<TeamObjective> teamObjectiveSet) {
        this.teamObjectiveSet = teamObjectiveSet;
    }

    public void setBanSet(Set<Ban> banSet) {
        this.banSet = banSet;
    }

    public Set<Ban> getBanSet() {
        return banSet;
    }

    public Set<TeamObjective> getTeamObjectiveSet() {
        return teamObjectiveSet;
    }

    public MatchTeamKey getKey() {
        return key;
    }

    public Match getMatch() {
        return match;
    }

    public Team getTeam() {
        return team;
    }
}
