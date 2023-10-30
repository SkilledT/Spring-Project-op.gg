package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.ItemComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemComponentRepository extends JpaRepository<ItemComponent, Long> {
}
