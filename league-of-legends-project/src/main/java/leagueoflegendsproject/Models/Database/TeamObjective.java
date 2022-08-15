package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.TeamObjectiveKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Team_objective")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamObjective that = (TeamObjective) o;
        return Objects.equals(matchTeam, that.matchTeam) && Objects.equals(objective, that.objective);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchTeam, objective);
    }
}
