package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, Long> {

    @Query("select e from Summoner e where e.summonerId = :summonerId")
    Optional<Summoner> findSummonerBySummonerId(@Param("summonerId") String summonerId);
}
