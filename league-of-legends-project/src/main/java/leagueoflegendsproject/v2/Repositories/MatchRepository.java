package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select e from Match e where e.externalMatchId = :matchId")
    Optional<Match> findByMatchId(String matchId);
}
