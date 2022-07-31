package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Repositories.MatchParticipantAveragePerformanceRepository;
import leagueoflegendsproject.Repositories.MatchParticipantRepository;
import leagueoflegendsproject.Utils.MatchParticipantUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleFunction;

import static java.util.stream.Collectors.groupingBy;

@Component
public class MatchParticipantAveragePerformanceScheduler {

    private final MatchParticipantRepository matchParticipantRepository;
    private final MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository;

    public MatchParticipantAveragePerformanceScheduler(MatchParticipantRepository matchParticipantRepository, MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
        this.matchParticipantAveragePerformanceRepository = matchParticipantAveragePerformanceRepository;
    }


    //@Scheduled(fixedRate = Constants.TimeConstants.MINUTE_TO_MILLISECONDS * 2)
    //@Transactional
    public void update() {

    }

    private MatchParticipantAveragePerformance getMatchParticipantAveragePerformanceFromMatchParticipantList(List<MatchParticipant> mParticipantsByIndividualPosition, Constants.MatchParticipantConstants.IndividualPosition individualPosition, String rank) {
        var averageVisionScore = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, MatchParticipant::getVisionScore);
        var averageKillParticipation = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, MatchParticipantUtils::getKillParticipation);
        var averageStolenObj = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, MatchParticipant::getObjectivesStolen);
        var averageDealtDamageToChampions = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, MatchParticipant::getTotalDamageDealtToChampions);
        var averageReceivedDamage = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, MatchParticipant::getTotalDamageTaken);
        var averagePentaKills = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, MatchParticipant::getPentakills);
        var averageCSPerMinute = MatchParticipantUtils.getAverageValueOfNaN(mParticipantsByIndividualPosition, (ToDoubleFunction<MatchParticipant>) e -> e.getTotalMinionsKilled() / (TimeUnit.SECONDS.toMinutes(e.getMatch().getGameDuration()) == 0 ? 1 : TimeUnit.SECONDS.toMinutes(e.getMatch().getGameDuration())));

        var standardDeviationOfVisionScore = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, MatchParticipant::getVisionScore);
        var standardDeviationOfKillParticipation = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, MatchParticipantUtils::getKillParticipation);
        var standardDeviationOfStolenObj = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, MatchParticipant::getObjectivesStolen);
        var standardDeviationOfDealtDamageToChampions = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, MatchParticipant::getTotalDamageDealtToChampions);
        var standardDeviationOfReceivedDamage = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, MatchParticipant::getTotalDamageTaken);
        var standardDeviationOfPentaKills = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, MatchParticipant::getPentakills);
        var standardDeviationOfCSPerMinute = MatchParticipantUtils.getStandardDeviation(mParticipantsByIndividualPosition, e -> e.getTotalMinionsKilled() / (TimeUnit.SECONDS.toMinutes(e.getMatch().getGameDuration()) == 0 ? 1 : TimeUnit.SECONDS.toMinutes(e.getMatch().getGameDuration())));


        return new MatchParticipantAveragePerformance(
                individualPosition,
                rank,
                averageKillParticipation,
                standardDeviationOfKillParticipation,
                averageVisionScore,
                standardDeviationOfVisionScore,
                averageStolenObj,
                standardDeviationOfStolenObj,
                averageDealtDamageToChampions,
                standardDeviationOfDealtDamageToChampions,
                averageReceivedDamage,
                standardDeviationOfReceivedDamage,
                averagePentaKills,
                standardDeviationOfPentaKills,
                averageCSPerMinute,
                standardDeviationOfCSPerMinute);
    }
}
