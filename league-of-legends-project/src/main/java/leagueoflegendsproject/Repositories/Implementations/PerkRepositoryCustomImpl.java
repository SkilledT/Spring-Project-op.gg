package leagueoflegendsproject.Repositories.Implementations;

import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Repositories.PerkRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PerkRepositoryCustomImpl implements PerkRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Perk> findMostPopularPerksMainTreeForChampionByItsName(String championName) {
        Query query = this.entityManager
                .createNativeQuery("select * from [dbo].perkMainTree(:championName, (SELECT [dbo].mostPopularPerTreeForChampion(:championName)))", "perkTreeMapping");
        query.setParameter("championName", championName);
        return query.getResultList();
    }

    @Override
    public List<Perk> findMostPopularPerksSubTreeForChampionByItsName(String championName) {
        Query query = this.entityManager
                .createNativeQuery("select * from [dbo].perkSubTree(:championName, (SELECT [dbo].mostPopularPerTreeForChampion(:championName)))", "perkTreeMapping");
        query.setParameter("championName", championName);
        List<Perk> perkList = query.getResultList();
        return perkList;
    }
}
