package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, String> {
}
