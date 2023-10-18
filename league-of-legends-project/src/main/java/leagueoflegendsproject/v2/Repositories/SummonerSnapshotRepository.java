package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.SummonerSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerSnapshotRepository extends JpaRepository<SummonerSnapshot, Long> {
}
