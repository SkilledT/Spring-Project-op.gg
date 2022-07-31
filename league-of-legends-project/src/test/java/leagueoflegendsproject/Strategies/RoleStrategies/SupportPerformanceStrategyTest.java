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

class SupportPerformanceStrategyTest {

    private MatchParticipantAveragePerformanceRepository mockMatchParticipantAveragePerformanceRepository;

    @BeforeEach
    public void setUp() {
        mockMatchParticipantAveragePerformanceRepository = mock(MatchParticipantAveragePerformanceRepository.class);
    }

    @Test
    void countPerformanceRate_ShouldReturnValidPerformedScore_WhenSupportPerformanceStrategyIsUsed() {
        // given
        String tier = "DIAMOND";
        Constants.MatchParticipantConstants.IndividualPosition individualPosition = Constants.MatchParticipantConstants.IndividualPosition.UTILITY;
        MatchParticipant matchParticipant = MatchParticipant.builder()
                .withIndividualPosition(individualPosition)
                .withKillParticipation(0.6)
                .withTotalMinionsKilled(100)
                .withVisionScore(25)
                .withIndividualPosition(individualPosition)
                .withSummoner(Summoner.builder().withTier(tier).build())
                .build();
        Optional<MatchParticipantAveragePerformance> matchParticipantAveragePerformance = Optional.ofNullable(MatchParticipantAveragePerformance.builder()
                .withIndividualPosition(individualPosition)
                .withTier(tier)
                .withAvgKillParticipation(new BigDecimal("0.5"))
                .withAvgVisionScore(new BigDecimal("0.5"))
                .withAvgTotalMinionsKilled(new BigDecimal("99"))
                .withStdevOfKillParticipation(new BigDecimal("0.7"))
                .withStdevOfVisionScore(new BigDecimal("0.7"))
                .withStdevOfTotalMinionsKilled(new BigDecimal("0.7"))
                .build());
        var expectedResult = 123.92;

        // when
        when(mockMatchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(tier, individualPosition))
                .thenReturn(matchParticipantAveragePerformance);

        var toTest = new SupportPerformanceStrategy(mockMatchParticipantAveragePerformanceRepository);
        var actualResult = toTest.countPerformanceRate(matchParticipant);

        // then
        assertEquals(expectedResult, actualResult, 0.1);
    }
}