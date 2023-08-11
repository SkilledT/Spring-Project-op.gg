package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, Long> {
}
