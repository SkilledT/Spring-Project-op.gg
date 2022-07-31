package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.MatchParticipant;

public interface PerformanceStrategy {
    Double countPerformanceRate(MatchParticipant matchParticipant);
    PerformanceStrategyName getStrategyName();
    Constants.MatchParticipantConstants.IndividualPosition getIndividualPosition();
}
