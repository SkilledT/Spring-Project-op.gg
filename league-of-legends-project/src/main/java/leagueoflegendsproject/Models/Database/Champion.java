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
@Table(name = "Champion")
public class Champion {

    @Id
    @Column(name = "champion_id")
    private Long id;

    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private Set<MatchParticipant> matchParticipantSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "champion")
    private Set<Ban> ban;

}
