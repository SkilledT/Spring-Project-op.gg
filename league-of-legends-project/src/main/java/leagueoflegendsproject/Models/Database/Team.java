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

    public Team(Long id){
        this.id = id;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<MatchParticipant> banSet;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<MatchTeam> teamObjectiveSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MatchParticipant> getBanSet() {
        return banSet;
    }

    public void setBanSet(Set<MatchParticipant> banSet) {
        this.banSet = banSet;
    }

    public Set<MatchTeam> getTeamObjectiveSet() {
        return teamObjectiveSet;
    }

    public void setTeamObjectiveSet(Set<MatchTeam> teamObjectiveSet) {
        this.teamObjectiveSet = teamObjectiveSet;
    }
}
