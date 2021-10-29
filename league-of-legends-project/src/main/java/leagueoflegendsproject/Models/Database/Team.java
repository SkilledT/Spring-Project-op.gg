package leagueoflegendsproject.Models.Database;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Team {

    @Id
    @Column(name = "team_id")
    private Long id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Ban> banSet;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<TeamObjective> teamObjectiveSet;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<MatchParticipant> matchParticipantSet;

}
