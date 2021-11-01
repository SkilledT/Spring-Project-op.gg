package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.TeamObjectiveKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Team_objective")
public class TeamObjective {

    @EmbeddedId
    private TeamObjectiveKey key;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "matchTeamKey")
    @JoinColumns({
            @JoinColumn(name = "match_id", referencedColumnName = "match_id"),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private MatchTeam matchTeam;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public void setKey(TeamObjectiveKey key) {
        this.key = key;
    }

    public void setMatchTeam(MatchTeam matchTeam) {
        this.matchTeam = matchTeam;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }
}
