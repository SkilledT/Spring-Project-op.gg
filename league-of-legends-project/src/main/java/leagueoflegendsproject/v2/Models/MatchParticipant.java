package leagueoflegendsproject.v2.Models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MatchParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean win;
    private Integer bountyLevel;
    private Integer totalUnitsHealed;
    private Integer largestMultiKill;
    private Integer spell2Cast;
    private Integer champExperience;
    private Integer turretTakedowns;
    private Integer damageDealtToObjectives;
    private Integer magicDamageTaken;
    private Integer deaths;
    private Integer objectivesStolen;
    private Integer detectorWardsPlaced;
    private Integer magicDamageDealtToChampions;
    private Integer wardsKilled;
    private Integer pentakills;
    private Integer spell3Casts;
    private Boolean firstTowerKill;
    private String individualPosition;
    private Integer wardsPlaced;
    private Integer totalDamageDealt;
    private Integer largestKillingSpree;
    private Integer totalDamageDealtToChampions;
    private Integer summoner2Id;
    private String role;
    private Integer totalTimeSpentDead;
    private Integer InhibitorKills;
    private Integer totalTimeCcDealt;
    private Integer participantId;
    private Boolean teamEarlySurrender;
    private Integer goldSpent;
    private Integer unrealKills;
    private Integer consumablesPurchased;
    private Integer visionScore;
    private Boolean firstBloodKill;
    private Integer longestTimeSpentLiving;
    private Integer sightWardsBoughtInGame;
    private Integer turretLost;
    private Integer quadrakills;
    private Integer nexusTakedowns;
    private Integer summoner1Id;
    private Integer totalDamageShieldedOnTeammates;
    private Integer summoner2Casts;
    private Integer goldEarned;
    private Integer nexusLost;
    private Integer physicalDamageTaken;
    private Integer champLvl;
    private Integer totalDamageTaken;
    private Integer neutralMinionsKilled;
    private Integer championTransform;
    private Integer tripleKills;
    private Integer damageSelfMitigated;
    private Integer inhibitorsLost;
    private Integer inhibitorTakedowns;
    private Integer largestCriticalStrike;
    private Integer totalHealsOnTeammates;
    private Integer summoner1Casts;
    private Integer damageDealtsToBuildings;
    private Integer magicDamageDealt;
    private Integer timePlayed;
    private String championName;
    private Integer timeCCingOthers;
    private String teamPosition;
    private Integer physicalDamageDealtToChampions;
    private Integer totalMinionsKilled;
    private Integer visionWardsBoughtInGame;
    private Integer kills;
    private Boolean firstTowerAssist;
    private Integer turretKills;
    private Boolean firstBloodAssist;
    private Integer trueDamageTaken;
    private Integer assists;
    private Integer itemsPurchased;
    private Integer objectivesStolenAssists;
    private Integer damageDealtsToTurrets;
    private Integer totalHeal;
    private String lane;
    private Boolean gameEndedInSurrender;
    private Integer physicalDamageDealt;
    private Integer trueDamageDealtToChampions;
    private Integer dragonKills;
    private Integer baronKills;
    private Integer doubleKills;
    private Integer nexusKills;
    private Integer trueDamageDealt;
    private Integer spell1Casts;
    private Boolean gameEndedInEarlySurrender;
    private Integer spell4Casts;
    private Double killParticipation;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ChampionSnapshot championSnapshot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SummonerSnapshot summonerSnapshot;

    @OneToMany(mappedBy = "matchParticipant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipantPerk> matchParticipantPerks = new HashSet<>();

    @OneToMany(mappedBy = "matchParticipant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipantItem> matchParticipantItems = new HashSet<>();

}
