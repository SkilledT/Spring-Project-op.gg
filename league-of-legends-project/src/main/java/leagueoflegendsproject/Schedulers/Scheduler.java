package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.Controllers.MatchController;
import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Repositories.MatchParticipantRepository;
import leagueoflegendsproject.Utils.MatchParticipantUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;

import static java.util.stream.Collectors.groupingBy;

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
