package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Services.HttpServices.HttpMatchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import static java.util.stream.Collectors.groupingBy;

@Component
public class Scheduler {

    @PersistenceContext
    EntityManager entityManager;

    private final HttpMatchService httpMatchService;

    public Scheduler(HttpMatchService httpMatchService) {
        this.httpMatchService = httpMatchService;
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
        System.out.println("triggered procedure: " + Constants.EntityConstants.Procedures.UPDATE_CHAMPION_PERKS);
        StoredProcedureQuery storedProcedureQuery =
                entityManager.createStoredProcedureQuery(Constants.EntityConstants.Procedures.UPDATE_CHAMPION_PERKS);
        storedProcedureQuery.execute();
    }

    @Scheduled(fixedRate = Constants.TimeConstants.HOUR_TO_MILLISECONDS * 2)
    public void updateChallengers() {
        System.out.println("Scheduled retrieving challengers has been started");
        httpMatchService.refreshChallengersData();
        System.out.println("Scheduled retrieving challengers has been completed");
    }

    @Scheduled(fixedRate = Constants.TimeConstants.MINUTE_TO_MILLISECONDS * 2)
    @Transactional
    public void updateMatchParticipantAveragePerformance() {
        long start = System.currentTimeMillis();
        System.out.println("updateMatchParticipantAveragePerformance scheduler started");
        StoredProcedureQuery storedProcedureQuery =
                entityManager.createStoredProcedureQuery(Constants.EntityConstants.Procedures.UPDATE_MATCH_PARTICIPANT_AVERAGE_PERFORMANCE_AGGREGATES);
        storedProcedureQuery.execute();

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("updateMatchParticipantAveragePerformance, Elapsed time: " + elapsedTimeSec);
    }

}
