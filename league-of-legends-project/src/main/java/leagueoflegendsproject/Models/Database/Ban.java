package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.BanKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Ban")
public class Ban {

    @EmbeddedId
    private BanKey banKey = new BanKey();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "matchTeamKey")
    @JoinColumns({
            @JoinColumn(name = "match_id", referencedColumnName = "match_id"),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    })
    private MatchTeam matchTeam;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "championId")
    @JoinColumn(name = "champion_id")
    private Champion champion;

    private Integer pickTurn;

    public Ban(BanKey banKey, MatchTeam matchTeam, Champion champion, Integer pickTurn) {
        this.banKey = banKey;
        this.matchTeam = matchTeam;
        this.champion = champion;
        this.pickTurn = pickTurn;
    }

    public Ban() {
    }

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
