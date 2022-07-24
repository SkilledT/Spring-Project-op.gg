package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.NumericalHelpers;
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
        MatchParticipantAveragePerformance matchParticipantAveragePerformance = matchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(matchParticipant.getSummoner().getTier(), matchParticipant.getIndividualPosition()).orElse(null);
        if (matchParticipantAveragePerformance == null)
            return null;
        final double csKillerPerMinuteWeight = 7.0;
        final double damageDealtWeight = 2.5;
        final double pentakillWeight = 0.5;

        var csKilledPerMinuteZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalMinionsKilled(), matchParticipantAveragePerformance.getAverageCSPerMinute().doubleValue(), matchParticipantAveragePerformance.getStandardDeviationOfCSPerMinute().doubleValue());
        var damageDealtZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getTotalDamageDealtToChampions(), matchParticipantAveragePerformance.getAverageDealtDamageToChampions().doubleValue(), matchParticipantAveragePerformance.getStandardDeviationOfDealtDamageToChampions().doubleValue());
        var pentakillZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getPentakills(), matchParticipantAveragePerformance.getAveragePentaKills().doubleValue(), matchParticipantAveragePerformance.getAveragePentaKills().doubleValue());

        var result = csKilledPerMinuteZScore * csKillerPerMinuteWeight + damageDealtZScore * damageDealtWeight + pentakillZScore * pentakillWeight;
        return result;
    }

    @Override
    public PerformanceStrategyName getStrategyName() {
        return PerformanceStrategyName.BottomPerformanceStrategy;
    }
}
