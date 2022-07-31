package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PerformanceStrategyFactory {
    private Map<PerformanceStrategyName, PerformanceStrategy> strategiesByStrategyName = new HashMap<>();
    private Map<Constants.MatchParticipantConstants.IndividualPosition, PerformanceStrategy> strategiesByIndividualPosition = new HashMap<>();

    @Autowired
    public PerformanceStrategyFactory(Set<PerformanceStrategy> performanceStrategySet) {
        createStrategy(performanceStrategySet);
        createStrategyByIndividualPosition(performanceStrategySet);
    }

    private void createStrategy(Set<PerformanceStrategy> strategySet) {
        strategySet.forEach(strategy -> strategiesByStrategyName.put(strategy.getStrategyName(), strategy));
    }

    private void createStrategyByIndividualPosition(Set<PerformanceStrategy> strategySet) {
        strategySet.forEach(strategy -> strategiesByIndividualPosition.put(strategy.getIndividualPosition(), strategy));
    }

    public PerformanceStrategy findStrategyByName(PerformanceStrategyName performanceStrategyName) {
        return this.strategiesByStrategyName.get(performanceStrategyName);
    }

    public PerformanceStrategy findStrategyByIndividualPosition(Constants.MatchParticipantConstants.IndividualPosition individualPosition) {
        return this.strategiesByIndividualPosition.get(individualPosition);
    }
}