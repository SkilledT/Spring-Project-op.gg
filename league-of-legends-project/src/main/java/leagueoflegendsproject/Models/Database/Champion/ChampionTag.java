package leagueoflegendsproject.Models.Database.Champion;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Champion_Tag")
public class ChampionTag {

    @EmbeddedId
    private ChampionTagKey championTagKey = new ChampionTagKey();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "champion_id")
    @MapsId("championId")
    private Champion champion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    @MapsId("tagId")
    private Tag tag;

    public ChampionTag(ChampionTagKey championTagKey, Champion champion, Tag tag) {
        this.championTagKey = championTagKey;
        this.champion = champion;
        this.tag = tag;
    }

    public ChampionTag() {
    }

    public ChampionTagKey getChampionTagKey() {
        return championTagKey;
    }

    public void setChampionTagKey(ChampionTagKey championTagKey) {
        this.championTagKey = championTagKey;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionTag that = (ChampionTag) o;
        return Objects.equals(championTagKey, that.championTagKey) &&
                Objects.equals(champion, that.champion) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championTagKey, champion, tag);
    }
}
