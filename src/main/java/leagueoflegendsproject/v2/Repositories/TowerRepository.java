package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Tower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TowerRepository extends JpaRepository<Tower, Long> {
}
