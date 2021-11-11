package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.TeamObjectiveKey;
import leagueoflegendsproject.Models.Database.TeamObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamObjectiveRepository extends JpaRepository<TeamObjective, TeamObjectiveKey> {

    @Override
    <S extends TeamObjective> S save(S s);
}
