package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Perk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerkRepository extends JpaRepository<Perk, Long> {
}
