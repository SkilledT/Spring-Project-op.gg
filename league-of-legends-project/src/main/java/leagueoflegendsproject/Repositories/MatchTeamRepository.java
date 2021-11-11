package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.MatchTeamKey;
import leagueoflegendsproject.Models.Database.MatchTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchTeamRepository extends JpaRepository<MatchTeam, MatchTeamKey> {

    @Override
    <S extends MatchTeam> S save(S s);
}
