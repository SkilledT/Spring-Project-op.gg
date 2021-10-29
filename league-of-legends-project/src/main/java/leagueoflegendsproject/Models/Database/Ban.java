package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.BanKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Ban")
public class Ban {

    @EmbeddedId
    private BanKey banKey;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "teamId")
    @JoinColumn(name = "team_team_id")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "championId")
    @JoinColumn(name = "champion_champion_id")
    private Champion champion;

    private Integer pickTurn;

}
