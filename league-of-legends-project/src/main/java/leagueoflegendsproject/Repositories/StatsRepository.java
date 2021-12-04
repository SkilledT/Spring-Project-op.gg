package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {

    @Override
    <S extends Stats> S save(S s);
}
