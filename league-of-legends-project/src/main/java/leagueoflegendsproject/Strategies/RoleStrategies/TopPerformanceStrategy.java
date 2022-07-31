package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Repositories.MatchParticipantAveragePerformanceRepository;
import leagueoflegendsproject.Utils.MatchParticipantUtils;
import org.springframework.stereotype.Component;

@Component
public class TopPerformanceStrategy implements PerformanceStrategy {

    private final MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository;

    public TopPerformanceStrategy(MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository) {
        this.matchParticipantAveragePerformanceRepository = matchParticipantAveragePerformanceRepository;
    }

    @Override
    public Double countPerformanceRate(MatchParticipant matchParticipant) {
        MatchParticipantAveragePerformance matchParticipantAveragePerformance = matchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(matchParticipant.getSummoner().getTier(), getIndividualPosition()).orElse(null);
        if (matchParticipantAveragePerformance == null)
            return 0.0;
        final double killParticipationWeight = 2.5;
        final double killedMinionsWeight = 5.0;
        final double visionScoreWeight = 1.0;
        final double receivedDamageWeight = 1.5;

        var killParticipationZScore = NumericalHelpers.Standardization.getZValue(MatchParticipantUtils.getKillParticipation(matchParticipant), matchParticipantAveragePerformance.getAvgKillParticipation().doubleValue(), matchParticipantAveragePerformance.getStdevOfKillParticipation().doubleValue());
        var killedMinionsZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalMinionsKilled(), matchParticipantAveragePerformance.getAvgTotalMinionsKilled().intValue(), matchParticipantAveragePerformance.getStdevOfTotalMinionsKilled().doubleValue());
        var visionScoreZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getVisionScore(), matchParticipantAveragePerformance.getAvgVisionScore().doubleValue(), matchParticipantAveragePerformance.getStdevOfVisionScore().doubleValue());
        var receivedDamageZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalDamageTaken(), matchParticipantAveragePerformance.getAvgReceivedDamage().doubleValue(), matchParticipantAveragePerformance.getStdevOfReceivedDamage().doubleValue());

        return killParticipationZScore * killParticipationWeight +
                killedMinionsZScore * killedMinionsWeight +
                visionScoreZScore * visionScoreWeight +
                receivedDamageZScore * receivedDamageWeight;
    }

    @Override
    public PerformanceStrategyName getStrategyName() {
        return PerformanceStrategyName.TopPerformanceStrategy;
    }

    @Override
    public Constants.MatchParticipantConstants.IndividualPosition getIndividualPosition() {
        return Constants.MatchParticipantConstants.IndividualPosition.TOP;
    }
}
