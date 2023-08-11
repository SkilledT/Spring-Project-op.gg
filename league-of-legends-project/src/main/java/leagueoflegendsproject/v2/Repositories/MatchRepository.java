package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
