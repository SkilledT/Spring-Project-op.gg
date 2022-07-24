package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Models.Database.MatchParticipant;
import org.springframework.stereotype.Component;

@Component
public class JunglePerformanceStrategy implements PerformanceStrategy{
    @Override
    public Double countPerformanceRate(MatchParticipant matchParticipant) {
        return null;
    }

    @Override
    public PerformanceStrategyName getStrategyName() {
        return PerformanceStrategyName.JunglePerformanceStrategy;
    }
}
