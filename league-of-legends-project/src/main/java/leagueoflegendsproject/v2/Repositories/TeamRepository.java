package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
