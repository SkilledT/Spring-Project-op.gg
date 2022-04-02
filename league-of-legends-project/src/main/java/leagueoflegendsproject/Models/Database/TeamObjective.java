package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.TeamObjectiveKey;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Team_objective")
public class TeamObjective {

    @EmbeddedId
    private TeamObjectiveKey key = new TeamObjectiveKey();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "matchTeamKey")
    @JoinColumns({
            @JoinColumn(name = "match_id", referencedColumnName = "match_id"),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private MatchTeam matchTeam;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "objectiveId")
    @JoinColumn(name = "objective_name")
    private Objective objective;

    private Integer kills;

    @Type(type = "numeric_boolean")
    private Boolean first;

    public TeamObjective(TeamObjectiveKey key, MatchTeam matchTeam, Objective objective, Integer kills, Boolean first) {
        this.key = key;
        this.matchTeam = matchTeam;
        this.objective = objective;
        this.kills = kills;
        this.first = first;
    }

    public TeamObjective() {
    }

    public TeamObjectiveKey getKey() {
        return key;
    }

    public void setKey(TeamObjectiveKey key) {
        this.key = key;
    }

    public MatchTeam getMatchTeam() {
        return matchTeam;
    }

    public void setMatchTeam(MatchTeam matchTeam) {
        this.matchTeam = matchTeam;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamObjective that = (TeamObjective) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(matchTeam, that.matchTeam) &&
                Objects.equals(objective, that.objective) &&
                Objects.equals(kills, that.kills) &&
                Objects.equals(first, that.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, matchTeam, objective, kills, first);
    }
}
