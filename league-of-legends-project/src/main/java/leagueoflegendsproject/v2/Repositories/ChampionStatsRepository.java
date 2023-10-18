package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.ChampionStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionStatsRepository extends JpaRepository<ChampionStats, Long> {

}
