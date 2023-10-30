package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.ObjectiveChampion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveChampionRepository extends JpaRepository<ObjectiveChampion, Long> {
}
