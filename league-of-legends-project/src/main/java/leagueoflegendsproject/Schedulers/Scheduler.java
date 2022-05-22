package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.Controllers.MatchController;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Component
public class Scheduler {

    @PersistenceContext
    EntityManager entityManager;

    public final MatchController matchController;

    public Scheduler(MatchController matchController) {
        this.matchController = matchController;
    }

    @Scheduled(cron = "@daily")
    public void updateChampionStats() {
        StoredProcedureQuery storedProcedureQuery =
                entityManager.createStoredProcedureQuery(Constants.EntityConstants.Procedures.FILL_CHAMPION_STATS);
        storedProcedureQuery.execute();
        System.out.println("triggered procedure: " + Constants.EntityConstants.Procedures.FILL_CHAMPION_STATS);
    }

    @Scheduled(cron = "@daily")
    public void updateChampionPerks() {
        StoredProcedureQuery storedProcedureQuery =
                entityManager.createStoredProcedureQuery(Constants.EntityConstants.Procedures.UPDATE_CHAMPION_PERKS);
        storedProcedureQuery.execute();
        System.out.println("triggered procedure: " + Constants.EntityConstants.Procedures.UPDATE_CHAMPION_PERKS);
    }

    @Scheduled(fixedRate = Constants.TimeConstants.HOUR_TO_MILLISECONDS * 2)
    public void updateChallengers() {
        matchController.refreshChallengersData();
        System.out.println("Scheduled retrieving challengers has been completed");
    }
}
