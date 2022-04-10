package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.ChampionStats;
import leagueoflegendsproject.Models.Database.Keys.ChampionStatsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionStatsRepository extends JpaRepository<ChampionStats, ChampionStatsKey> {

    List<ChampionStats> findTop3ChampionStatsByChampionNameOrderByWinRatioDesc(String championName);
    List<ChampionStats> findTop3ChampionStatsByChampionNameOrderByWinRatioAsc(String championName);

}
