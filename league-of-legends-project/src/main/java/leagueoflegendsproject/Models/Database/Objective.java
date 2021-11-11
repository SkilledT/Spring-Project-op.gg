package leagueoflegendsproject.Models.Database;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Objective")
public class Objective {

    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "objective")
    private Set<TeamObjective> teamObjective = new HashSet<>();

    public Objective(String name){
        this.name = name;
    }
    public Objective(){}

    public Objective(String name, Set<TeamObjective> teamObjective) {
        this.name = name;
        this.teamObjective = teamObjective;
    }

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }

    public Set<TeamObjective> getTeamObjective() {
        return teamObjective;
    }

    public void setTeamObjective(Set<TeamObjective> teamObjective) {
        this.teamObjective = teamObjective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(name, objective.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
