package leagueoflegendsproject.Models.Database;


import com.fasterxml.jackson.annotation.JsonBackReference;
import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "match_participant")
public class MatchParticipant {

    @EmbeddedId
    private MatchParticipantKey matchParticipantKey;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "summoner_summoner_id")
    @MapsId(value = "summonerId")
    private Summoner summoner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Match_match_id")
    @MapsId(value = "matchId")
    private Match match;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "champion_id")
    private Champion champion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Team_team_id")
    private Team team;

    @OneToMany(mappedBy = "matchParticipant")
    private Set<ParticipantItems> participantItemsSet;

    @Type(type = "numeric_boolean")
    private Boolean win;
    private Integer bountyLevel;
    private Integer totalUnitsHealed;
    private Integer largestMultiKill;
    @Column(name = "spell2_cast")
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
    @Column(name = "spell3_casts")
    private Integer spell3Casts;
    @Type(type = "numeric_boolean")
    private Boolean firstTowerKill;
    private String individualPosition;
    private Integer wardsPlaced;
    private Integer totalDamageDealt;
    private Integer largestKillingSpree;
    private Integer totalDamageDealtToChampions;
    @Column(name = "summoner2_id")
    private Integer summoner2Id;
    private String role;
    private Integer totalTimeSpentDead;
    private Integer InhibitorKills;
    private Integer totalTimeCcDealt;
    private Integer participantId;
    @Type(type = "numeric_boolean")
    private Boolean teamEarlySurrender;
    private Integer goldSpent;
    private Integer unrealKills;
    private Integer consumablesPurchased;
    private Integer visionScore;
    @Type(type = "numeric_boolean")
    private Boolean firstBloodKill;
    private Integer longestTimeSpentLiving;
    private Integer sightWardsBoughtInGame;
    private Integer turretLost;
    private Integer quadrakills;
    private Integer nexusTakedowns;
    @Column(name = "summoner1_id")
    private Integer summoner1Id;
    private Integer totalDamageShieldedOnTeammates;
    @Column(name = "summoner2_casts")
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
    @Column(name = "summoner1_casts")
    private Integer summoner1Casts;
    private Integer damageDealtsToBuildings;
    private Integer magicDamageDealt;
    private Integer timePlayed;
    private String championName;
    @Column(name = "time_ccing_others")
    private Integer timeCCingOthers;
    private String teamPosition;
    private Integer physicalDamageDealtToChampions;
    private Integer totalMinionsKilled;
    private Integer visionWardsBoughtInGame;
    private Integer Kills;
    @Type(type = "numeric_boolean")
    private Boolean firstTowerAssist;
    private Integer turretKills;
    @Type(type = "numeric_boolean")
    private Boolean firstBloodAssist;
    private Integer trueDamageTaken;
    private Integer assists;
    private Integer itemsPurchased;
    private Integer objectivesStolenAssists;
    private Integer damageDealtsToTurrets;
    private Integer totalHeal;
    private String lane;
    @Type(type = "numeric_boolean")
    private Boolean gameEndedInSurrender;
    private Integer physicalDamageDealt;
    private Integer trueDamageDealtToChampions;
    private Integer dragonKills;
    private Integer baronKills;
    private Integer doubleKills;
    private Integer nexusKills;
    private Integer trueDamageDealt;
    @Column(name = "spell1_casts")
    private Integer spell1Casts;
    @Type(type = "numeric_boolean")
    private Boolean gameEndedInEarlySurrender;
    @Column(name = "spell4_casts")
    private Integer spell4Casts;

    public MatchParticipant(ParticipantsItem participant){

    }


    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setParticipantItemsSet(Set<ParticipantItems> participantItemsSet) {
        this.participantItemsSet = participantItemsSet;
    }

    @Override
    public String toString() {
        return "MatchParticipant{" +
                "matchParticipantKey=" + matchParticipantKey +
                ", summoner=" + summoner +
                ", match=" + match +
                ", champion=" + champion +
                ", team=" + team +
                ", participantItemsSet=" + participantItemsSet +
                ", win=" + win +
                ", bountyLevel=" + bountyLevel +
                ", totalUnitsHealed=" + totalUnitsHealed +
                ", largestMultiKill=" + largestMultiKill +
                ", spell2Cast=" + spell2Cast +
                ", champExperience=" + champExperience +
                ", turretTakedowns=" + turretTakedowns +
                ", damageDealtToObjectives=" + damageDealtToObjectives +
                ", magicDamageTaken=" + magicDamageTaken +
                ", deaths=" + deaths +
                ", objectivesStolen=" + objectivesStolen +
                ", detectorWardsPlaced=" + detectorWardsPlaced +
                ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
                ", wardsKilled=" + wardsKilled +
                ", pentakills=" + pentakills +
                ", spell3Casts=" + spell3Casts +
                ", firstTowerKill=" + firstTowerKill +
                ", individualPosition='" + individualPosition + '\'' +
                ", wardsPlaced=" + wardsPlaced +
                ", totalDamageDealt=" + totalDamageDealt +
                ", largestKillingSpree=" + largestKillingSpree +
                ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
                ", summoner2Id=" + summoner2Id +
                ", role='" + role + '\'' +
                ", totalTimeSpentDead=" + totalTimeSpentDead +
                ", InhibitorKills=" + InhibitorKills +
                ", totalTimeCcDealt=" + totalTimeCcDealt +
                ", participantId=" + participantId +
                ", teamEarlySurrender=" + teamEarlySurrender +
                ", goldSpent=" + goldSpent +
                ", unrealKills=" + unrealKills +
                ", consumablesPurchased=" + consumablesPurchased +
                ", visionScore=" + visionScore +
                ", firstBloodKill=" + firstBloodKill +
                ", longestTimeSpentLiving=" + longestTimeSpentLiving +
                ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
                ", turretLost=" + turretLost +
                ", quadrakills=" + quadrakills +
                ", nexusTakedowns=" + nexusTakedowns +
                ", summoner1Id=" + summoner1Id +
                ", totalDamageShieldedOnTeammates=" + totalDamageShieldedOnTeammates +
                ", summoner2Casts=" + summoner2Casts +
                ", goldEarned=" + goldEarned +
                ", nexusLost=" + nexusLost +
                ", physicalDamageTaken=" + physicalDamageTaken +
                ", champLvl=" + champLvl +
                ", totalDamageTaken=" + totalDamageTaken +
                ", neutralMinionsKilled=" + neutralMinionsKilled +
                ", championTransform=" + championTransform +
                ", tripleKills=" + tripleKills +
                ", damageSelfMitigated=" + damageSelfMitigated +
                ", inhibitorsLost=" + inhibitorsLost +
                ", inhibitorTakedowns=" + inhibitorTakedowns +
                ", largestCriticalStrike=" + largestCriticalStrike +
                ", totalHealsOnTeammates=" + totalHealsOnTeammates +
                ", summoner1Casts=" + summoner1Casts +
                ", damageDealtsToBuildings=" + damageDealtsToBuildings +
                ", magicDamageDealt=" + magicDamageDealt +
                ", timePlayed=" + timePlayed +
                ", championName='" + championName + '\'' +
                ", timeCCingOthers=" + timeCCingOthers +
                ", teamPosition='" + teamPosition + '\'' +
                ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
                ", totalMinionsKilled=" + totalMinionsKilled +
                ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
                ", Kills=" + Kills +
                ", firstTowerAssist=" + firstTowerAssist +
                ", turretKills=" + turretKills +
                ", firstBloodAssist=" + firstBloodAssist +
                ", trueDamageTaken=" + trueDamageTaken +
                ", assists=" + assists +
                ", itemsPurchased=" + itemsPurchased +
                ", objectivesStolenAssists=" + objectivesStolenAssists +
                ", damageDealtsToTurrets=" + damageDealtsToTurrets +
                ", totalHeal=" + totalHeal +
                ", lane='" + lane + '\'' +
                ", gameEndedInSurrender=" + gameEndedInSurrender +
                ", physicalDamageDealt=" + physicalDamageDealt +
                ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
                ", dragonKills=" + dragonKills +
                ", baronKills=" + baronKills +
                ", doubleKills=" + doubleKills +
                ", nexusKills=" + nexusKills +
                ", trueDamageDealt=" + trueDamageDealt +
                ", spell1Casts=" + spell1Casts +
                ", gameEndedInEarlySurrender=" + gameEndedInEarlySurrender +
                ", spell4Casts=" + spell4Casts +
                '}';
    }
}
