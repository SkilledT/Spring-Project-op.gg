package leagueoflegendsproject.Strategies.RoleStrategies;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.Database.Summoner;
import leagueoflegendsproject.Repositories.MatchParticipantAveragePerformanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BottomPerformanceStrategyTest {

    private MatchParticipantAveragePerformanceRepository mockMatchParticipantAveragePerformanceRepository;

    @BeforeEach
    public void setUp() {
        mockMatchParticipantAveragePerformanceRepository = mock(MatchParticipantAveragePerformanceRepository.class);
    }

    @Test
    void countPerformanceRate_ShouldReturnValidPerformedScore_WhenBottomPerformanceStrategyIsUsed() {
        // given
        String tier = "DIAMOND";
        Constants.MatchParticipantConstants.IndividualPosition individualPosition = Constants.MatchParticipantConstants.IndividualPosition.BOTTOM;
        MatchParticipant matchParticipant = MatchParticipant.builder()
                .withIndividualPosition(individualPosition)
                .withTotalMinionsKilled(188)
                .withTotalDamageDealtToChampions(24000)
                .withPentakills(1)
                .withIndividualPosition(individualPosition)
                .withSummoner(Summoner.builder().withTier(tier).build())
                .build();
        Optional<MatchParticipantAveragePerformance> matchParticipantAveragePerformance = Optional.ofNullable(MatchParticipantAveragePerformance.builder()
                .withIndividualPosition(individualPosition)
                .withTier(tier)
                .withAvgTotalMinionsKilled(new BigDecimal("166.00"))
                .withAvgPentakill(new BigDecimal("0.00"))
                .withAvgDealtDamageToChampions(new BigDecimal("3403.00"))
                .withStdevOfTotalMinionsKilled(new BigDecimal("44.40"))
                .withStdevOfPentakills(new BigDecimal("0.10"))
                .withStdevOfDealtDamageToChampions(new BigDecimal("12513.29"))
                .build());
        var expectedResult = 7.58;

        // when
        when(mockMatchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(tier, individualPosition))
                .thenReturn(matchParticipantAveragePerformance);

        var toTest = new BottomPerformanceStrategy(mockMatchParticipantAveragePerformanceRepository);
        var actualResult = toTest.countPerformanceRate(matchParticipant);

        // then
        assertEquals(expectedResult, actualResult, 0.1);
    }
}