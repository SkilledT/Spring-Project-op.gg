package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.NumericalHelpers;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Repositories.MatchParticipantAveragePerformanceRepository;
import leagueoflegendsproject.Utils.MatchParticipantUtils;
import org.springframework.stereotype.Component;

@Component
public class JunglePerformanceStrategy implements PerformanceStrategy {

    private final MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository;

    public JunglePerformanceStrategy(MatchParticipantAveragePerformanceRepository matchParticipantAveragePerformanceRepository) {
        this.matchParticipantAveragePerformanceRepository = matchParticipantAveragePerformanceRepository;
    }

    @Override
    public Double countPerformanceRate(MatchParticipant matchParticipant) {
        MatchParticipantAveragePerformance matchParticipantAveragePerformance = matchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(matchParticipant.getSummoner().getTier(), getIndividualPosition()).orElse(null);
        if (matchParticipantAveragePerformance == null)
            return 0.0;
        final double killParticipationWeight = 0.3;
        final double stolenObjsWeight = 0.25;
        final double pentakillWeight = 0.25;
        final double killedNeutralMonstersWeight = 0.5;
        final double visionScoreWeight = 1.5;

        var killParticipationZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getKillParticipation(), matchParticipantAveragePerformance.getAvgKillParticipation().doubleValue(), matchParticipantAveragePerformance.getStdevOfKillParticipation().doubleValue());
        var stolenObjectsZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getObjectivesStolen(), matchParticipantAveragePerformance.getAvgStolenObj().doubleValue(), matchParticipantAveragePerformance.getStdevOfStolenObj().doubleValue());
        var pentakillZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getPentakills(), matchParticipantAveragePerformance.getAvgPentakill().doubleValue(), matchParticipantAveragePerformance.getAvgPentakill().doubleValue());
        var visionScoreZScore = NumericalHelpers.Standardization.getZValue(matchParticipant.getVisionScore(), matchParticipantAveragePerformance.getAvgVisionScore().doubleValue(), matchParticipantAveragePerformance.getStdevOfVisionScore().doubleValue());

        return killParticipationZScore * killParticipationWeight + stolenObjectsZScore * stolenObjsWeight + pentakillZScore * pentakillWeight + visionScoreZScore * visionScoreWeight;
    }

    @Override
    public Constants.MatchParticipantConstants.IndividualPosition getIndividualPosition() {
        return Constants.MatchParticipantConstants.IndividualPosition.JUNGLE;
    }
}
