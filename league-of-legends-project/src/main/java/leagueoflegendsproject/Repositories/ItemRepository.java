package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
