package leagueoflegendsproject.Models.Database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @Column(name = "team_id")
    private Integer id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<MatchTeam> teamObjectiveSet = new HashSet<>();

    public void addMatchParticipantChild(MatchParticipant matchParticipant) {
        this.matchParticipantSet.add(matchParticipant);
        matchParticipant.setTeam(this);
    }

    public void addMatchTeamChild(MatchTeam matchTeam) {
        this.teamObjectiveSet.add(matchTeam);
        matchTeam.setTeam(this);
    }

    public Team(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                '}';
    }
}
