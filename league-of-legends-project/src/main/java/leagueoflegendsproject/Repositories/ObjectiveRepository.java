package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, String> {

}
