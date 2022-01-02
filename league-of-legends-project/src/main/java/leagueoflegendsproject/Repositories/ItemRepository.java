package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Repositories.Interfaces.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Override
    Optional<Item> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);
}
