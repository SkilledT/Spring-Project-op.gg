package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Baron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaronRepository extends JpaRepository<Baron, Integer> {
}
