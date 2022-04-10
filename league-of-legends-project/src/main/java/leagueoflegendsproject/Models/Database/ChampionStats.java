package leagueoflegendsproject.Models.Database;

import leagueoflegendsproject.Models.Database.Keys.ChampionStatsKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Champion_Stats")
@IdClass(ChampionStatsKey.class)
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

    public ChampionStats(String championName, String enemyChampion, Integer games, Double winRatio) {
        this.championName = championName;
        this.enemyChampion = enemyChampion;
        this.games = games;
        this.winRatio = winRatio;
    }

    public ChampionStats() {
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getEnemyChampion() {
        return enemyChampion;
    }

    public void setEnemyChampion(String enemyChampion) {
        this.enemyChampion = enemyChampion;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(Double winRatio) {
        this.winRatio = winRatio;
    }

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
