package leagueoflegendsproject.Repositories.Implementations;

import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Repositories.Interfaces.ItemRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Item> getMostPopularItemsForChampion(String championName) {
        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("get_most_popular_items_for_champion", "basicItemMapping")
                        .registerStoredProcedureParameter("championName", String.class, ParameterMode.IN);
        query.setParameter("championName", championName);
        query.execute();
        List<Item> mostPopularItemsList = query.getResultList();
        return mostPopularItemsList;
    }
}
