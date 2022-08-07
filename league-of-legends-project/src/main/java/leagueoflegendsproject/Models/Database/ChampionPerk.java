package leagueoflegendsproject.Models.Database;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.ChampionPerkKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "champion_perk")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionPerk {

    @EmbeddedId
    private ChampionPerkKey championPerkKey = new ChampionPerkKey();

    @Column(name = "type", length = 10000)
    private String type;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "championId")
    @JoinColumn(name = "champion_id")
    private Champion champion;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId(value = "perkId")
    @JoinColumn(name = "perk_id")
    private Perk perk;

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
