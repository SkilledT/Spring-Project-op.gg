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
@Table(name = "Objective")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Objective {

    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "objective", cascade = CascadeType.ALL)
    private Set<TeamObjective> teamObjectiveSet = new HashSet<>();

    public Objective(String name){
        this.name = name;
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
