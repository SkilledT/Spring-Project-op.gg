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
    @MapsId(value = "teamId")
    @JoinColumn(name = "team_team_id")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "objectiveId")
    @JoinColumn(name = "objective_name")
    private Objective objective;

    private Integer kills;

    @Type(type = "numeric_boolean")
    private Boolean first;
}
