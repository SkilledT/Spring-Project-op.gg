package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Inhibitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InhibitorRepository extends JpaRepository<Inhibitor, Long> {
}
