package leagueoflegendsproject.Repositories.Interfaces;

import leagueoflegendsproject.Models.Database.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryCustom {

    List<Item> getMostPopularItemsForChampion(String championName);
}
