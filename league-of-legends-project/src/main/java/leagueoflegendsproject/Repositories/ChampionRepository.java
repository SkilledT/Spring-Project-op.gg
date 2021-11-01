package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

    Optional<Champion> findById(Long aLong);
}
