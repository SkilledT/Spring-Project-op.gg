package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.TeamObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamObjectiveRepository extends JpaRepository<TeamObjective, Long> {
}
