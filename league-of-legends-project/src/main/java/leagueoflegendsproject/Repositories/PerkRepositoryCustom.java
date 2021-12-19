package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Perk;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerkRepositoryCustom {

    List<Perk> findMostPopularPerksMainTreeForChampionByItsName(String championName);
    List<Perk> findMostPopularPerksSubTreeForChampionByItsName(String championName);
}
