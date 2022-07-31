package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Repositories.MatchParticipantAveragePerformanceRepository;
import org.springframework.stereotype.Component;

@Component
public class BottomPerformanceStrategy implements PerformanceStrategy {

    private final MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository;

    public BottomPerformanceStrategy(MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository) {
        this.matchParticipantAveragePerformanceRepository = matchParticipantAveragePerformanceRepository;
    }

    @Override
    public Double countPerformanceRate(MatchParticipant matchParticipant) {
        MatchParticipantAveragePerformance matchParticipantAveragePerformance = matchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(matchParticipant.getSummoner().getTier(), getIndividualPosition()).orElse(null);
        if (matchParticipantAveragePerformance == null)
            return 0.0;
        final double csKillerPerMinuteWeight = 7.0;
        final double damageDealtWeight = 2.5;
        final double pentakillWeight = 0.5;

        var csKilledPerMinuteZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalMinionsKilled(), matchParticipantAveragePerformance.getAvgTotalMinionsKilled().doubleValue(), matchParticipantAveragePerformance.getStdevOfTotalMinionsKilled().doubleValue());
        var damageDealtZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalDamageDealtToChampions(), matchParticipantAveragePerformance.getAvgDealtDamageToChampions().doubleValue(), matchParticipantAveragePerformance.getStdevOfDealtDamageToChampions().doubleValue());
        var pentakillZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getPentakills(), matchParticipantAveragePerformance.getAvgPentakill().doubleValue(), matchParticipantAveragePerformance.getAvgPentakill().doubleValue());

        var result = csKilledPerMinuteZScore * csKillerPerMinuteWeight + damageDealtZScore * damageDealtWeight + pentakillZScore * pentakillWeight;
        return result;
    }

    @Override
    public PerformanceStrategyName getStrategyName() {
        return PerformanceStrategyName.BottomPerformanceStrategy;
    }

    @Override
    public Constants.MatchParticipantConstants.IndividualPosition getIndividualPosition() {
        return Constants.MatchParticipantConstants.IndividualPosition.BOTTOM;
    }
}
