package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BanKey implements Serializable {

    private Long teamId;

    private Long championId;

    public BanKey() {
    }

    public BanKey(Long teamId, Long championId) {
        this.teamId = teamId;
        this.championId = championId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getChampionId() {
        return championId;
    }

    public void setChampionId(Long championId) {
        this.championId = championId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanKey banKey = (BanKey) o;
        return Objects.equals(teamId, banKey.teamId) &&
                Objects.equals(championId, banKey.championId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, championId);
    }
}
