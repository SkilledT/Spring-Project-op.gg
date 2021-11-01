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

    public BanKey getBanKey() {
        return banKey;
    }

    public void setBanKey(BanKey banKey) {
        this.banKey = banKey;
    }

    public MatchTeam getMatchTeam() {
        return matchTeam;
    }

    public void setMatchTeam(MatchTeam matchTeam) {
        this.matchTeam = matchTeam;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Integer getPickTurn() {
        return pickTurn;
    }

    public void setPickTurn(Integer pickTurn) {
        this.pickTurn = pickTurn;
    }
}
