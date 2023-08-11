package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Dragon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DragonRepository extends JpaRepository<Dragon, Long> {
}
