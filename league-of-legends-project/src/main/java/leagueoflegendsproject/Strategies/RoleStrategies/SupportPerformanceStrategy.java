package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Repositories.MatchParticipantAveragePerformanceRepository;
import org.springframework.stereotype.Component;

@Component
public class SupportPerformanceStrategy implements PerformanceStrategy {

    private final MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository;

    public SupportPerformanceStrategy(MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository) {
        this.matchParticipantAveragePerformanceRepository = matchParticipantAveragePerformanceRepository;
    }


    @Override
    public Double countPerformanceRate(MatchParticipant matchParticipant) {
        MatchParticipantAveragePerformance matchParticipantAveragePerformance = matchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(matchParticipant.getSummoner().getTier(), getIndividualPosition()).orElse(null);
        if (matchParticipantAveragePerformance == null)
            return 0.0;
        final double killParticipationWeight = 5.0;
        final double killedMinionsWeight = 0.5;
        final double visionScoreWeight = 3.5;
        final double utilityScoreWeight = 2.0;

        var killParticipationZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getKillParticipation(), matchParticipantAveragePerformance.getAvgKillParticipation().doubleValue(), matchParticipantAveragePerformance.getStdevOfKillParticipation().doubleValue());
        var killedMinionsZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalMinionsKilled(), matchParticipantAveragePerformance.getAvgTotalMinionsKilled().intValue(), matchParticipantAveragePerformance.getStdevOfTotalMinionsKilled().doubleValue());
        var visionScoreZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getVisionScore(), matchParticipantAveragePerformance.getAvgVisionScore().doubleValue(), matchParticipantAveragePerformance.getStdevOfVisionScore().doubleValue());

        return killParticipationZScore * killParticipationWeight +
                killedMinionsZScore * killedMinionsWeight +
                visionScoreZScore * visionScoreWeight;

    }

    @Override
    public Constants.MatchParticipantConstants.IndividualPosition getIndividualPosition() {
        return Constants.MatchParticipantConstants.IndividualPosition.UTILITY;
    }
}
