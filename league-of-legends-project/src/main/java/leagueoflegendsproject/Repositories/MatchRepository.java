package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {

    @Override
    <S extends Match> S save(S s);
}
