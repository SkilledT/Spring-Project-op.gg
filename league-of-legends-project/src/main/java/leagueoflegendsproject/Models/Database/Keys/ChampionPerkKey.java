package leagueoflegendsproject.Models.Database.Keys;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ChampionPerkKey implements Serializable {

    @Column(name = "champion_id")
    private Integer championId;
    @Column(name = "perk_id")
    private Integer perkId;

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getPerkId() {
        return perkId;
    }

    public void setPerkId(Integer perkId) {
        this.perkId = perkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionPerkKey that = (ChampionPerkKey) o;
        return Objects.equals(championId, that.championId) && Objects.equals(perkId, that.perkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championId, perkId);
    }
}
