package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.BanKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "Ban")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ban {

    @EmbeddedId
    private BanKey banKey = new BanKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "matchTeamKey")
    @JoinColumns({
            @JoinColumn(name = "match_id", referencedColumnName = "match_id"),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private MatchTeam matchTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId(value = "championId")
    @JoinColumn(name = "champion_id")
    private Champion champion;

    private Integer pickTurn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ban ban = (Ban) o;
        return Objects.equals(banKey, ban.banKey) &&
                Objects.equals(matchTeam, ban.matchTeam) &&
                Objects.equals(champion, ban.champion) &&
                Objects.equals(pickTurn, ban.pickTurn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banKey, matchTeam, champion, pickTurn);
    }
}
