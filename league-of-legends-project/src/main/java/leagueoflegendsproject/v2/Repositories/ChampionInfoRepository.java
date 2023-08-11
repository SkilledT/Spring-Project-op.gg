package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.ChampionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionInfoRepository extends JpaRepository<ChampionInfo, Long> {
}
