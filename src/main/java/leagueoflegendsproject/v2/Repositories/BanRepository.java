package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, Long> {
}
