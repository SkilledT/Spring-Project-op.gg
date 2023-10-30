package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select e from Team e where e.match.externalMatchId = :externalMatchId and e.externalTeamId = :externalTeamId")
    Optional<Team> findByExternalMatchIdAndExternalTeamId(String externalMatchId, String externalTeamId);
}
