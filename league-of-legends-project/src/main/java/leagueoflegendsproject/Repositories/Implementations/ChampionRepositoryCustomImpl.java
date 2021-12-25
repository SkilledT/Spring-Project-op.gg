package leagueoflegendsproject.Repositories.Implementations;

import leagueoflegendsproject.Models.Database.TemporaryTables.ChampionWithWinRatioEntity;
import leagueoflegendsproject.Repositories.Interfaces.ChampionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class ChampionRepositoryCustomImpl implements ChampionRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ChampionWithWinRatioEntity> getChampionWithWinRatioEntity(String championName, int minimumMatches) {
        List<ChampionWithWinRatioEntity> championWithWinRatioEntityList = new ArrayList<>();
        StoredProcedureQuery storedProcedureQuery =
                entityManager.createStoredProcedureQuery("champion_with_win_ratio",
                        "ChampionWithWinRatio")
                        .registerStoredProcedureParameter("championName", String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("minimumMatches", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("championName", championName);
        storedProcedureQuery.setParameter("minimumMatches", minimumMatches);
        storedProcedureQuery.execute();
        championWithWinRatioEntityList = storedProcedureQuery.getResultList();
        return championWithWinRatioEntityList;
    }
}
