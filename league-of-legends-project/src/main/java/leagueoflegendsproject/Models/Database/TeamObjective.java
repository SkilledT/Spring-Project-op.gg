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
}
