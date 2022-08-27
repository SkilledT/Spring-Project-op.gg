package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "match_participant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(setterPrefix = "with")
public class MatchParticipant {

    @EmbeddedId
    private MatchParticipantKey matchParticipantKey = new MatchParticipantKey();

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Team_team_id")
    private Team team;

    @OneToMany(mappedBy = "matchParticipant", cascade = CascadeType.ALL)
    private Set<ParticipantItems> participantItemsSet = new HashSet<>();

    @OneToMany(mappedBy = "matchParticipant", cascade = CascadeType.ALL)
    private Set<MatchParticipantPerk> matchParticipantPerkSet = new HashSet<>();

    public void addParticipantItemChild(ParticipantItems participantsItems) {
        this.participantItemsSet.add(participantsItems);
        participantsItems.setMatchParticipant(this);
    }

    public void addMatchParticipantPerkChild(MatchParticipantPerk matchParticipantPerk) {
        this.matchParticipantPerkSet.add(matchParticipantPerk);
        matchParticipantPerk.setMatchParticipant(this);
    }

    @Type(type = "numeric_boolean")
    @Column(name = "win")
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
    @Column(name = "deaths")
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
    @Column(name = "individual_position")
    @Enumerated(EnumType.STRING)
    private Constants.MatchParticipantConstants.IndividualPosition individualPosition;
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
    @Column(name = "champ_lvl")
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
    @Column(name = "champion_Name")
    private String championName;
    @Column(name = "time_ccing_others")
    private Integer timeCCingOthers;
    private String teamPosition;
    private Integer physicalDamageDealtToChampions;
    @Column(name = "total_minions_killed")
    private Integer totalMinionsKilled;
    @Column(name = "vision_wards_bought_in_game")
    private Integer visionWardsBoughtInGame;
    @Column(name = "kills")
    private Integer Kills;
    @Type(type = "numeric_boolean")
    private Boolean firstTowerAssist;
    private Integer turretKills;
    @Type(type = "numeric_boolean")
    private Boolean firstBloodAssist;
    private Integer trueDamageTaken;
    @Column(name = "assists")
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
    @Column(name = "kill_participation", columnDefinition = "double precision")
    private Double killParticipation;

    public MatchParticipant(ParticipantsItem participant){
        this.win = participant.isWin();
        this.bountyLevel = participant.getBountyLevel();
        this.totalUnitsHealed = participant.getTotalUnitsHealed();
        this.largestMultiKill = participant.getLargestMultiKill();
        this.spell2Cast = participant.getSpell2Casts();
        this.champExperience = participant.getChampExperience();
        this.turretTakedowns = participant.getTurretTakedowns();
        this.damageDealtToObjectives = participant.getDamageDealtToObjectives();
        this.magicDamageTaken = participant.getMagicDamageTaken();
        this.deaths = participant.getDeaths();
        this.objectivesStolen = participant.getObjectivesStolen();
        this.detectorWardsPlaced = participant.getDetectorWardsPlaced();
        this.magicDamageDealtToChampions = participant.getMagicDamageDealtToChampions();
        this.wardsKilled = participant.getWardsKilled();
        this.pentakills = participant.getPentaKills();
        this.spell3Casts = participant.getSpell3Casts();
        this.firstTowerKill = participant.isFirstTowerKill();
        this.individualPosition = participant.getIndividualPosition();
        this.wardsPlaced = participant.getWardsPlaced();
        this.totalDamageDealt = participant.getTotalDamageDealt();
        this.largestKillingSpree = participant.getLargestKillingSpree();
        this.totalDamageDealtToChampions = participant.getTotalDamageDealtToChampions();
        this.summoner2Id = participant.getSummoner2Id();
        this.role = participant.getRole();
        this.totalTimeSpentDead = participant.getTotalTimeSpentDead();
        this.InhibitorKills = participant.getInhibitorKills();
        this.totalTimeCcDealt = participant.getTotalTimeCCDealt();
        this.participantId = participant.getParticipantId();
        this.teamEarlySurrender = participant.isGameEndedInEarlySurrender();
        this.goldSpent = participant.getGoldSpent();
        this.unrealKills = participant.getUnrealKills();
        this.consumablesPurchased = participant.getConsumablesPurchased();
        this.visionScore = participant.getVisionScore();
        this.firstBloodKill = participant.isFirstBloodKill();
        this.longestTimeSpentLiving = participant.getLongestTimeSpentLiving();
        this.sightWardsBoughtInGame = participant.getSightWardsBoughtInGame();
        this.turretLost = participant.getInhibitorsLost();
        this.quadrakills = participant.getQuadraKills();
        this.nexusTakedowns = participant.getNexusTakedowns();
        this.summoner1Id = participant.getSummoner1Id();
        this.totalDamageShieldedOnTeammates = participant.getTotalDamageShieldedOnTeammates();
        this.summoner2Casts = participant.getSummoner2Casts();
        this.goldEarned = participant.getGoldEarned();
        this.nexusLost = participant.getNexusLost();
        this.physicalDamageTaken = participant.getPhysicalDamageTaken();
        this.champLvl = participant.getChampLevel();
        this.totalDamageTaken = participant.getTotalDamageTaken();
        this.neutralMinionsKilled = participant.getNeutralMinionsKilled();
        this.championTransform = participant.getChampionTransform();
        this.tripleKills = participant.getTripleKills();
        this.damageSelfMitigated = participant.getDamageSelfMitigated();
        this.inhibitorsLost = participant.getInhibitorsLost();
        this.inhibitorTakedowns = participant.getInhibitorTakedowns();
        this.largestCriticalStrike = participant.getLargestCriticalStrike();
        this.totalHealsOnTeammates = participant.getTotalHealsOnTeammates();
        this.summoner1Casts = participant.getSummoner1Casts();
        this.damageDealtsToBuildings = participant.getDamageDealtToBuildings();
        this.magicDamageDealt = participant.getMagicDamageDealt();
        this.timePlayed = participant.getTimePlayed();
        this.championName = participant.getChampionName();
        this.timeCCingOthers = participant.getTimeCCingOthers();
        this.teamPosition = participant.getTeamPosition();
        this.physicalDamageDealtToChampions = participant.getPhysicalDamageDealtToChampions();
        this.totalMinionsKilled = participant.getTotalMinionsKilled();
        this.visionWardsBoughtInGame = participant.getVisionWardsBoughtInGame();
        this.Kills = participant.getKills();
        this.firstTowerAssist = participant.isFirstTowerAssist();
        this.turretKills = participant.getTurretKills();
        this.firstBloodAssist = participant.isFirstBloodAssist();
        this.trueDamageTaken = participant.getTrueDamageTaken();
        this.assists = participant.getAssists();
        this.itemsPurchased = participant.getItemsPurchased();
        this.objectivesStolenAssists = participant.getObjectivesStolenAssists();
        this.damageDealtsToTurrets = participant.getDamageDealtToTurrets();
        this.totalHeal = participant.getTotalHeal();
        this.lane = participant.getLane();
        this.gameEndedInSurrender = participant.isGameEndedInSurrender();
        this.physicalDamageDealt = participant.getPhysicalDamageDealt();
        this.trueDamageDealtToChampions = participant.getTrueDamageDealtToChampions();
        this.dragonKills = participant.getDragonKills();
        this.baronKills = participant.getBaronKills();
        this.doubleKills = participant.getDoubleKills();
        this.nexusKills = participant.getNexusKills();
        this.trueDamageDealt = participant.getTrueDamageDealt();
        this.spell1Casts = participant.getSpell1Casts();
        this.gameEndedInEarlySurrender = participant.isGameEndedInEarlySurrender();
        this.spell4Casts = participant.getSpell4Casts();
    }

    public MatchParticipant(MatchParticipantKey matchParticipantKey, Summoner summoner, Match match, Champion champion, Team team, Set<ParticipantItems> participantItemsSet, Boolean win, Integer bountyLevel, Integer totalUnitsHealed, Integer largestMultiKill, Integer spell2Cast, Integer champExperience, Integer turretTakedowns, Integer damageDealtToObjectives, Integer magicDamageTaken, Integer deaths, Integer objectivesStolen, Integer detectorWardsPlaced, Integer magicDamageDealtToChampions, Integer wardsKilled, Integer pentakills, Integer spell3Casts, Boolean firstTowerKill, Constants.MatchParticipantConstants.IndividualPosition individualPosition, Integer wardsPlaced, Integer totalDamageDealt, Integer largestKillingSpree, Integer totalDamageDealtToChampions, Integer summoner2Id, String role, Integer totalTimeSpentDead, Integer inhibitorKills, Integer totalTimeCcDealt, Integer participantId, Boolean teamEarlySurrender, Integer goldSpent, Integer unrealKills, Integer consumablesPurchased, Integer visionScore, Boolean firstBloodKill, Integer longestTimeSpentLiving, Integer sightWardsBoughtInGame, Integer turretLost, Integer quadrakills, Integer nexusTakedowns, Integer summoner1Id, Integer totalDamageShieldedOnTeammates, Integer summoner2Casts, Integer goldEarned, Integer nexusLost, Integer physicalDamageTaken, Integer champLvl, Integer totalDamageTaken, Integer neutralMinionsKilled, Integer championTransform, Integer tripleKills, Integer damageSelfMitigated, Integer inhibitorsLost, Integer inhibitorTakedowns, Integer largestCriticalStrike, Integer totalHealsOnTeammates, Integer summoner1Casts, Integer damageDealtsToBuildings, Integer magicDamageDealt, Integer timePlayed, String championName, Integer timeCCingOthers, String teamPosition, Integer physicalDamageDealtToChampions, Integer totalMinionsKilled, Integer visionWardsBoughtInGame, Integer kills, Boolean firstTowerAssist, Integer turretKills, Boolean firstBloodAssist, Integer trueDamageTaken, Integer assists, Integer itemsPurchased, Integer objectivesStolenAssists, Integer damageDealtsToTurrets, Integer totalHeal, String lane, Boolean gameEndedInSurrender, Integer physicalDamageDealt, Integer trueDamageDealtToChampions, Integer dragonKills, Integer baronKills, Integer doubleKills, Integer nexusKills, Integer trueDamageDealt, Integer spell1Casts, Boolean gameEndedInEarlySurrender, Integer spell4Casts, Set<MatchParticipantPerk> matchParticipantPerk) {
        this.matchParticipantKey = matchParticipantKey;
        this.summoner = summoner;
        this.match = match;
        this.champion = champion;
        this.team = team;
        this.participantItemsSet = participantItemsSet;
        this.win = win;
        this.bountyLevel = bountyLevel;
        this.totalUnitsHealed = totalUnitsHealed;
        this.largestMultiKill = largestMultiKill;
        this.spell2Cast = spell2Cast;
        this.champExperience = champExperience;
        this.turretTakedowns = turretTakedowns;
        this.damageDealtToObjectives = damageDealtToObjectives;
        this.magicDamageTaken = magicDamageTaken;
        this.deaths = deaths;
        this.objectivesStolen = objectivesStolen;
        this.detectorWardsPlaced = detectorWardsPlaced;
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
        this.wardsKilled = wardsKilled;
        this.pentakills = pentakills;
        this.spell3Casts = spell3Casts;
        this.firstTowerKill = firstTowerKill;
        this.individualPosition = individualPosition;
        this.wardsPlaced = wardsPlaced;
        this.totalDamageDealt = totalDamageDealt;
        this.largestKillingSpree = largestKillingSpree;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.summoner2Id = summoner2Id;
        this.role = role;
        this.totalTimeSpentDead = totalTimeSpentDead;
        InhibitorKills = inhibitorKills;
        this.totalTimeCcDealt = totalTimeCcDealt;
        this.participantId = participantId;
        this.teamEarlySurrender = teamEarlySurrender;
        this.goldSpent = goldSpent;
        this.unrealKills = unrealKills;
        this.consumablesPurchased = consumablesPurchased;
        this.visionScore = visionScore;
        this.firstBloodKill = firstBloodKill;
        this.longestTimeSpentLiving = longestTimeSpentLiving;
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
        this.turretLost = turretLost;
        this.quadrakills = quadrakills;
        this.nexusTakedowns = nexusTakedowns;
        this.summoner1Id = summoner1Id;
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
        this.summoner2Casts = summoner2Casts;
        this.goldEarned = goldEarned;
        this.nexusLost = nexusLost;
        this.physicalDamageTaken = physicalDamageTaken;
        this.champLvl = champLvl;
        this.totalDamageTaken = totalDamageTaken;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.championTransform = championTransform;
        this.tripleKills = tripleKills;
        this.damageSelfMitigated = damageSelfMitigated;
        this.inhibitorsLost = inhibitorsLost;
        this.inhibitorTakedowns = inhibitorTakedowns;
        this.largestCriticalStrike = largestCriticalStrike;
        this.totalHealsOnTeammates = totalHealsOnTeammates;
        this.summoner1Casts = summoner1Casts;
        this.damageDealtsToBuildings = damageDealtsToBuildings;
        this.magicDamageDealt = magicDamageDealt;
        this.timePlayed = timePlayed;
        this.championName = championName;
        this.timeCCingOthers = timeCCingOthers;
        this.teamPosition = teamPosition;
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
        this.totalMinionsKilled = totalMinionsKilled;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        Kills = kills;
        this.firstTowerAssist = firstTowerAssist;
        this.turretKills = turretKills;
        this.firstBloodAssist = firstBloodAssist;
        this.trueDamageTaken = trueDamageTaken;
        this.assists = assists;
        this.itemsPurchased = itemsPurchased;
        this.objectivesStolenAssists = objectivesStolenAssists;
        this.damageDealtsToTurrets = damageDealtsToTurrets;
        this.totalHeal = totalHeal;
        this.lane = lane;
        this.gameEndedInSurrender = gameEndedInSurrender;
        this.physicalDamageDealt = physicalDamageDealt;
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
        this.dragonKills = dragonKills;
        this.baronKills = baronKills;
        this.doubleKills = doubleKills;
        this.nexusKills = nexusKills;
        this.trueDamageDealt = trueDamageDealt;
        this.spell1Casts = spell1Casts;
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
        this.spell4Casts = spell4Casts;
        this.matchParticipantPerkSet = matchParticipantPerk;
    }

    public MatchParticipant(Team team,
                            Match match,
                            Summoner summoner,
                            Champion champion,
                            Boolean win,
                            Integer Kills,
                            Integer deaths,
                            Integer assists,
                            Integer totalMinionsKilled,
                            Integer champLvl,
                            String championName,
                            Constants.MatchParticipantConstants.IndividualPosition individualPosition,
                            Integer summoner1Id,
                            Integer summoner2Id,
                            Integer visionWardsBoughtInGame){
        this.team = team;
        this.match = match;
        this.summoner = summoner;
        this.champion = champion;
        this.win = win;
        this.Kills = Kills;
        this.deaths = deaths;
        this.assists = assists;
        this.totalMinionsKilled = totalMinionsKilled;
        this.champLvl = champLvl;
        this.championName = championName;
        this.individualPosition = individualPosition;
        this.summoner1Id = summoner1Id;
        this.summoner2Id = summoner2Id;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchParticipant that = (MatchParticipant) o;
        return Objects.equals(matchParticipantKey, that.matchParticipantKey) && Objects.equals(summoner, that.summoner) && Objects.equals(match, that.match) && Objects.equals(champion, that.champion) && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantKey, summoner, match, champion, team);
    }
}
