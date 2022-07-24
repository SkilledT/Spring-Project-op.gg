package leagueoflegendsproject.Strategies.RoleStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PerformanceStrategyFactory {
    private Map<PerformanceStrategyName, PerformanceStrategy> strategies = new HashMap<>();

    @Autowired
    public PerformanceStrategyFactory(Set<PerformanceStrategy> performanceStrategySet) {
        createStrategy(performanceStrategySet);
    }

    private void createStrategy(Set<PerformanceStrategy> strategySet) {
        strategySet.forEach(strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    public PerformanceStrategy findStrategyByName(PerformanceStrategyName performanceStrategyName) {
        return this.strategies.get(performanceStrategyName);
    }
}