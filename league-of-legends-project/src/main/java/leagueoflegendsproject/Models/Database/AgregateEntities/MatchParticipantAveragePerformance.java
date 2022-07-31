package leagueoflegendsproject.Models.Database.AgregateEntities;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
@Builder(setterPrefix = "with")
public class MatchParticipantAveragePerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Constants.MatchParticipantConstants.IndividualPosition individualPosition;
    private String tier;
    private BigDecimal avgKillParticipation;
    private BigDecimal avgVisionScore;
    private BigDecimal avgStolenObj;
    private BigDecimal avgDealtDamageToChampions;
    private BigDecimal avgReceivedDamage;
    private BigDecimal avgPentakill;
    private BigDecimal avgTotalMinionsKilled;

    private BigDecimal stdevOfKillParticipation;
    private BigDecimal stdevOfVisionScore;
    private BigDecimal stdevOfStolenObj;
    private BigDecimal stdevOfDealtDamageToChampions;
    private BigDecimal stdevOfReceivedDamage;
    private BigDecimal stdevOfPentakills;
    private BigDecimal stdevOfTotalMinionsKilled;

    public MatchParticipantAveragePerformance(Constants.MatchParticipantConstants.IndividualPosition individualPosition, String tier, BigDecimal avgKillParticipation, BigDecimal stdevOfKillParticipation, BigDecimal avgVisionScore, BigDecimal stdevOfVisionScore, BigDecimal avgStolenObj, BigDecimal stdevOfStolenObj, BigDecimal avgDealtDamageToChampions, BigDecimal stdevOfDealtDamageToChampions, BigDecimal avgReceivedDamage, BigDecimal stdevOfReceivedDamage, BigDecimal avgPentakill, BigDecimal stdevOfPentakills, BigDecimal avgTotalMinionsKilled, BigDecimal stdevOfTotalMinionsKilled) {
        this(null, individualPosition, tier, avgKillParticipation, stdevOfKillParticipation, avgVisionScore, stdevOfVisionScore, avgStolenObj, stdevOfStolenObj, avgDealtDamageToChampions, stdevOfDealtDamageToChampions, avgReceivedDamage, stdevOfReceivedDamage, avgPentakill, stdevOfPentakills, avgTotalMinionsKilled, stdevOfTotalMinionsKilled);
    }
}