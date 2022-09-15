package leagueoflegendsproject.Models.Database;

import leagueoflegendsproject.Models.Database.Keys.ChampionStatsKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Champion_Stats")
@IdClass(ChampionStatsKey.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionStats {

    @Id
    @Column(name = "champion_name")
    private String championName;
    @Id
    @Column(name = "enemy_champion")
    private String enemyChampion;

    @Column(name = "games")
    private Integer games;
    @Column(name = "win_ratio")
    private Double winRatio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionStats that = (ChampionStats) o;
        return Objects.equals(championName, that.championName) && Objects.equals(enemyChampion, that.enemyChampion) && Objects.equals(games, that.games) && Objects.equals(winRatio, that.winRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championName, enemyChampion, games, winRatio);
    }
}
