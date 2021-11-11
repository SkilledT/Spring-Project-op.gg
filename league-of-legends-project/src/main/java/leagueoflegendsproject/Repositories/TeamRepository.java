package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findById(Integer aLong);
}
