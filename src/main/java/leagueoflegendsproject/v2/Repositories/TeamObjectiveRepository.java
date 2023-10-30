package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Team;
import leagueoflegendsproject.v2.Models.TeamObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamObjectiveRepository extends JpaRepository<TeamObjective, Long> {

    @Query("select e from TeamObjective e where e.team.externalTeamId = :externalTeamId")
    Optional<TeamObjective> findByTeamExternalId(@Param("externalTeamId") Integer externalTeamId);
}
