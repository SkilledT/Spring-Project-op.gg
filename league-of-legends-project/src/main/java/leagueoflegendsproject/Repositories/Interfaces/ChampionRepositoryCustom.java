package leagueoflegendsproject.Repositories.Interfaces;


import leagueoflegendsproject.Models.Database.TemporaryTables.ChampionWithWinRatioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionRepositoryCustom {

    List<ChampionWithWinRatioEntity> getChampionWithWinRatioEntity(String name, int minimumMatches);
}
