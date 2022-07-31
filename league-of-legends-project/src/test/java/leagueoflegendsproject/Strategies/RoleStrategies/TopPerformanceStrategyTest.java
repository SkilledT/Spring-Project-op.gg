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

class TopPerformanceStrategyTest {

    private MatchParticipantAveragePerformanceRepository mockMatchParticipantAveragePerformanceRepository;

    @BeforeEach
    public void setUp() {
        mockMatchParticipantAveragePerformanceRepository = mock(MatchParticipantAveragePerformanceRepository.class);
    }

    @Test
    void countPerformanceRate_ShouldReturnValidPerformedScore_WhenMidPerformanceStrategyIsUsed() {
        // given
        String tier = "DIAMOND";
        Constants.MatchParticipantConstants.IndividualPosition individualPosition = Constants.MatchParticipantConstants.IndividualPosition.TOP;
        MatchParticipant matchParticipant = MatchParticipant.builder()
                .withIndividualPosition(individualPosition)
                .withKillParticipation(0.6)
                .withTotalMinionsKilled(100)
                .withTotalDamageTaken(1000)
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
                .withAvgReceivedDamage(new BigDecimal("950.00"))
                .withStdevOfKillParticipation(new BigDecimal("0.7"))
                .withStdevOfVisionScore(new BigDecimal("0.7"))
                .withStdevOfTotalMinionsKilled(new BigDecimal("0.7"))
                .withStdevOfReceivedDamage(new BigDecimal("0.7"))
                .build());
        var expectedResult = 149.64;

        // when
        when(mockMatchParticipantAveragePerformanceRepository.findByTierAndIndividualPosition(tier, individualPosition))
                .thenReturn(matchParticipantAveragePerformance);

        var toTest = new TopPerformanceStrategy(mockMatchParticipantAveragePerformanceRepository);
        var actualResult = toTest.countPerformanceRate(matchParticipant);

        // then
        assertEquals(expectedResult, actualResult, 0.1);
    }
}