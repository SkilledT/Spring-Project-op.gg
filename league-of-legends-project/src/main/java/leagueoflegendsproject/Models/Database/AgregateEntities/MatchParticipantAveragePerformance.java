package leagueoflegendsproject.Models.Database.AgregateEntities;

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
public class MatchParticipantAveragePerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String individualPosition;
    private String tier;
    private BigDecimal avgKillParticipation;
    private BigDecimal avgKillParticipationStandardDeviation;
    private BigDecimal avgVisionScore;
    private BigDecimal avgVisionScoreStandardDeviation;
    private BigDecimal averageStolenObj;
    private BigDecimal standardDeviationOfStolenObj;
    private BigDecimal averageDealtDamageToChampions;
    private BigDecimal standardDeviationOfDealtDamageToChampions;
    private BigDecimal averageReceivedDamage;
    private BigDecimal standardDeviationOfReceivedDamage;
    private BigDecimal averagePentaKills;
    private BigDecimal standardDeviationOfPentaKills;
    private BigDecimal averageCSPerMinute;
    private BigDecimal standardDeviationOfCSPerMinute;

    public MatchParticipantAveragePerformance(String individualPosition, String tier, BigDecimal avgKillParticipation, BigDecimal avgKillParticipationStandardDeviation, BigDecimal avgVisionScore, BigDecimal avgVisionScoreStandardDeviation, BigDecimal averageStolenObj, BigDecimal standardDeviationOfStolenObj, BigDecimal averageDealtDamageToChampions, BigDecimal standardDeviationOfDealtDamageToChampions, BigDecimal averageReceivedDamage, BigDecimal standardDeviationOfReceivedDamage, BigDecimal averagePentaKills, BigDecimal standardDeviationOfPentaKills, BigDecimal averageCSPerMinute, BigDecimal standardDeviationOfCSPerMinute) {
        this(null, individualPosition, tier, avgKillParticipation, avgKillParticipationStandardDeviation, avgVisionScore, avgVisionScoreStandardDeviation, averageStolenObj, standardDeviationOfStolenObj, averageDealtDamageToChampions, standardDeviationOfDealtDamageToChampions, averageReceivedDamage, standardDeviationOfReceivedDamage, averagePentaKills, standardDeviationOfPentaKills, averageCSPerMinute, standardDeviationOfCSPerMinute);
    }
}