package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Models.Database.MatchParticipant;

public interface PerformanceStrategy {
    Double countPerformanceRate(MatchParticipant matchParticipant);
    PerformanceStrategyName getStrategyName();
}
