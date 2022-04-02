package leagueoflegendsproject.Models.Database;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.ChampionPerkKey;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "champion_perk")
@Entity
public class ChampionPerk {

    @EmbeddedId
    private ChampionPerkKey championPerkKey = new ChampionPerkKey();

    @Column(name = "type")
    private String type;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "championId")
    @JoinColumn(name = "champion_id")
    private Champion champion;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "perkId")
    @JoinColumn(name = "perk_id")
    private Perk perk;

    public ChampionPerk() {
    }

    public ChampionPerk(ChampionPerkKey championPerkKey, String type, Champion champion, Perk perk) {
        this.championPerkKey = championPerkKey;
        this.type = type;
        this.champion = champion;
        this.perk = perk;
    }

    public ChampionPerkKey getChampionPerkKey() {
        return championPerkKey;
    }

    public void setChampionPerkKey(ChampionPerkKey championPerkKey) {
        this.championPerkKey = championPerkKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Perk getPerk() {
        return perk;
    }

    public void setPerk(Perk perk) {
        this.perk = perk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionPerk that = (ChampionPerk) o;
        return Objects.equals(championPerkKey, that.championPerkKey) && Objects.equals(type, that.type) && Objects.equals(champion, that.champion) && Objects.equals(perk, that.perk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championPerkKey, type, champion, perk);
    }
}
