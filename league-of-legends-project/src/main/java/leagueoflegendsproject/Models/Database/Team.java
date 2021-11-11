package leagueoflegendsproject.Models.Database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Team")
public class Team {

    @Id
    @Column(name = "team_id")
    private Integer id;

    @OneToMany(mappedBy = "team")
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<MatchTeam> teamObjectiveSet = new HashSet<>();

    public Team(Integer id, Set<MatchParticipant> matchParticipantSet, Set<MatchTeam> teamObjectiveSet) {
        this.id = id;
        this.matchParticipantSet = matchParticipantSet;
        this.teamObjectiveSet = teamObjectiveSet;
    }

    public Team() {
    }

    public Team(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<MatchParticipant> getMatchParticipantSet() {
        return matchParticipantSet;
    }

    public void setMatchParticipantSet(Set<MatchParticipant> matchParticipantSet) {
        this.matchParticipantSet = matchParticipantSet;
    }

    public Set<MatchTeam> getTeamObjectiveSet() {
        return teamObjectiveSet;
    }

    public void setTeamObjectiveSet(Set<MatchTeam> teamObjectiveSet) {
        this.teamObjectiveSet = teamObjectiveSet;
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
}
