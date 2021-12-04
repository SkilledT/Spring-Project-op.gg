package leagueoflegendsproject.Models.Database.Champion;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChampionTagKey implements Serializable {
    private Integer championId;
    private Integer tagId;

    public ChampionTagKey(Integer championId, Integer tagId) {
        this.championId = championId;
        this.tagId = tagId;
    }

    public ChampionTagKey() {
    }

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionTagKey that = (ChampionTagKey) o;
        return Objects.equals(championId, that.championId) &&
                Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championId, tagId);
    }
}
