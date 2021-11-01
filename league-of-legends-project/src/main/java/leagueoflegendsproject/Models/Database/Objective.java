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
@Table(name = "Objective")
public class Objective {

    @Id
    @Column(name = "name")
    private String id;

    @OneToMany(mappedBy = "objective", cascade = CascadeType.ALL)
    private Set<TeamObjective> teamObjective;

    public Objective(String name){
        this.id = name;
    }
}
