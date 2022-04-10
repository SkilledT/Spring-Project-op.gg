package leagueoflegendsproject.Models.Database.Keys;

import java.io.Serializable;
import java.util.Objects;

public class ChampionStatsKey implements Serializable {

    private String championName;
    private String enemyChampion;

    public ChampionStatsKey() {
    }

    public ChampionStatsKey(String championName, String enemyChampion) {
        this.championName = championName;
        this.enemyChampion = enemyChampion;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionStatsKey that = (ChampionStatsKey) o;
        return Objects.equals(championName, that.championName) && Objects.equals(enemyChampion, that.enemyChampion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(championName, enemyChampion);
    }
}
