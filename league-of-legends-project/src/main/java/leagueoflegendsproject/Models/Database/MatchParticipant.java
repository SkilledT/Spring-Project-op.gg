package leagueoflegendsproject.Models.Database;


import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "match_participant")
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
    @Nullable
    private Champion champion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Team_team_id")
    @Nullable
    private Team team;

    @OneToMany(mappedBy = "matchParticipant")
    private Set<ParticipantItems> participantItemsSet = new HashSet<>();

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

    public MatchParticipant(MatchParticipantKey matchParticipantKey, Summoner summoner, Match match, Champion champion, Team team, Set<ParticipantItems> participantItemsSet, Boolean win, Integer bountyLevel, Integer totalUnitsHealed, Integer largestMultiKill, Integer spell2Cast, Integer champExperience, Integer turretTakedowns, Integer damageDealtToObjectives, Integer magicDamageTaken, Integer deaths, Integer objectivesStolen, Integer detectorWardsPlaced, Integer magicDamageDealtToChampions, Integer wardsKilled, Integer pentakills, Integer spell3Casts, Boolean firstTowerKill, String individualPosition, Integer wardsPlaced, Integer totalDamageDealt, Integer largestKillingSpree, Integer totalDamageDealtToChampions, Integer summoner2Id, String role, Integer totalTimeSpentDead, Integer inhibitorKills, Integer totalTimeCcDealt, Integer participantId, Boolean teamEarlySurrender, Integer goldSpent, Integer unrealKills, Integer consumablesPurchased, Integer visionScore, Boolean firstBloodKill, Integer longestTimeSpentLiving, Integer sightWardsBoughtInGame, Integer turretLost, Integer quadrakills, Integer nexusTakedowns, Integer summoner1Id, Integer totalDamageShieldedOnTeammates, Integer summoner2Casts, Integer goldEarned, Integer nexusLost, Integer physicalDamageTaken, Integer champLvl, Integer totalDamageTaken, Integer neutralMinionsKilled, Integer championTransform, Integer tripleKills, Integer damageSelfMitigated, Integer inhibitorsLost, Integer inhibitorTakedowns, Integer largestCriticalStrike, Integer totalHealsOnTeammates, Integer summoner1Casts, Integer damageDealtsToBuildings, Integer magicDamageDealt, Integer timePlayed, String championName, Integer timeCCingOthers, String teamPosition, Integer physicalDamageDealtToChampions, Integer totalMinionsKilled, Integer visionWardsBoughtInGame, Integer kills, Boolean firstTowerAssist, Integer turretKills, Boolean firstBloodAssist, Integer trueDamageTaken, Integer assists, Integer itemsPurchased, Integer objectivesStolenAssists, Integer damageDealtsToTurrets, Integer totalHeal, String lane, Boolean gameEndedInSurrender, Integer physicalDamageDealt, Integer trueDamageDealtToChampions, Integer dragonKills, Integer baronKills, Integer doubleKills, Integer nexusKills, Integer trueDamageDealt, Integer spell1Casts, Boolean gameEndedInEarlySurrender, Integer spell4Casts) {
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
    }

    public MatchParticipant() {
    }


    public MatchParticipantKey getMatchParticipantKey() {
        return matchParticipantKey;
    }

    public void setMatchParticipantKey(MatchParticipantKey matchParticipantKey) {
        this.matchParticipantKey = matchParticipantKey;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<ParticipantItems> getParticipantItemsSet() {
        return participantItemsSet;
    }

    public void setParticipantItemsSet(Set<ParticipantItems> participantItemsSet) {
        this.participantItemsSet = participantItemsSet;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public Integer getBountyLevel() {
        return bountyLevel;
    }

    public void setBountyLevel(Integer bountyLevel) {
        this.bountyLevel = bountyLevel;
    }

    public Integer getTotalUnitsHealed() {
        return totalUnitsHealed;
    }

    public void setTotalUnitsHealed(Integer totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }

    public Integer getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(Integer largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public Integer getSpell2Cast() {
        return spell2Cast;
    }

    public void setSpell2Cast(Integer spell2Cast) {
        this.spell2Cast = spell2Cast;
    }

    public Integer getChampExperience() {
        return champExperience;
    }

    public void setChampExperience(Integer champExperience) {
        this.champExperience = champExperience;
    }

    public Integer getTurretTakedowns() {
        return turretTakedowns;
    }

    public void setTurretTakedowns(Integer turretTakedowns) {
        this.turretTakedowns = turretTakedowns;
    }

    public Integer getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }

    public void setDamageDealtToObjectives(Integer damageDealtToObjectives) {
        this.damageDealtToObjectives = damageDealtToObjectives;
    }

    public Integer getMagicDamageTaken() {
        return magicDamageTaken;
    }

    public void setMagicDamageTaken(Integer magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getObjectivesStolen() {
        return objectivesStolen;
    }

    public void setObjectivesStolen(Integer objectivesStolen) {
        this.objectivesStolen = objectivesStolen;
    }

    public Integer getDetectorWardsPlaced() {
        return detectorWardsPlaced;
    }

    public void setDetectorWardsPlaced(Integer detectorWardsPlaced) {
        this.detectorWardsPlaced = detectorWardsPlaced;
    }

    public Integer getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }

    public void setMagicDamageDealtToChampions(Integer magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }

    public Integer getWardsKilled() {
        return wardsKilled;
    }

    public void setWardsKilled(Integer wardsKilled) {
        this.wardsKilled = wardsKilled;
    }

    public Integer getPentakills() {
        return pentakills;
    }

    public void setPentakills(Integer pentakills) {
        this.pentakills = pentakills;
    }

    public Integer getSpell3Casts() {
        return spell3Casts;
    }

    public void setSpell3Casts(Integer spell3Casts) {
        this.spell3Casts = spell3Casts;
    }

    public Boolean getFirstTowerKill() {
        return firstTowerKill;
    }

    public void setFirstTowerKill(Boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public void setIndividualPosition(String individualPosition) {
        this.individualPosition = individualPosition;
    }

    public Integer getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(Integer wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public Integer getTotalDamageDealt() {
        return totalDamageDealt;
    }

    public void setTotalDamageDealt(Integer totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }

    public Integer getLargestKillingSpree() {
        return largestKillingSpree;
    }

    public void setLargestKillingSpree(Integer largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }

    public Integer getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public void setTotalDamageDealtToChampions(Integer totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }

    public Integer getSummoner2Id() {
        return summoner2Id;
    }

    public void setSummoner2Id(Integer summoner2Id) {
        this.summoner2Id = summoner2Id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getTotalTimeSpentDead() {
        return totalTimeSpentDead;
    }

    public void setTotalTimeSpentDead(Integer totalTimeSpentDead) {
        this.totalTimeSpentDead = totalTimeSpentDead;
    }

    public Integer getInhibitorKills() {
        return InhibitorKills;
    }

    public void setInhibitorKills(Integer inhibitorKills) {
        InhibitorKills = inhibitorKills;
    }

    public Integer getTotalTimeCcDealt() {
        return totalTimeCcDealt;
    }

    public void setTotalTimeCcDealt(Integer totalTimeCcDealt) {
        this.totalTimeCcDealt = totalTimeCcDealt;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Boolean getTeamEarlySurrender() {
        return teamEarlySurrender;
    }

    public void setTeamEarlySurrender(Boolean teamEarlySurrender) {
        this.teamEarlySurrender = teamEarlySurrender;
    }

    public Integer getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(Integer goldSpent) {
        this.goldSpent = goldSpent;
    }

    public Integer getUnrealKills() {
        return unrealKills;
    }

    public void setUnrealKills(Integer unrealKills) {
        this.unrealKills = unrealKills;
    }

    public Integer getConsumablesPurchased() {
        return consumablesPurchased;
    }

    public void setConsumablesPurchased(Integer consumablesPurchased) {
        this.consumablesPurchased = consumablesPurchased;
    }

    public Integer getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(Integer visionScore) {
        this.visionScore = visionScore;
    }

    public Boolean getFirstBloodKill() {
        return firstBloodKill;
    }

    public void setFirstBloodKill(Boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    public Integer getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }

    public void setLongestTimeSpentLiving(Integer longestTimeSpentLiving) {
        this.longestTimeSpentLiving = longestTimeSpentLiving;
    }

    public Integer getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }

    public void setSightWardsBoughtInGame(Integer sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }

    public Integer getTurretLost() {
        return turretLost;
    }

    public void setTurretLost(Integer turretLost) {
        this.turretLost = turretLost;
    }

    public Integer getQuadrakills() {
        return quadrakills;
    }

    public void setQuadrakills(Integer quadrakills) {
        this.quadrakills = quadrakills;
    }

    public Integer getNexusTakedowns() {
        return nexusTakedowns;
    }

    public void setNexusTakedowns(Integer nexusTakedowns) {
        this.nexusTakedowns = nexusTakedowns;
    }

    public Integer getSummoner1Id() {
        return summoner1Id;
    }

    public void setSummoner1Id(Integer summoner1Id) {
        this.summoner1Id = summoner1Id;
    }

    public Integer getTotalDamageShieldedOnTeammates() {
        return totalDamageShieldedOnTeammates;
    }

    public void setTotalDamageShieldedOnTeammates(Integer totalDamageShieldedOnTeammates) {
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
    }

    public Integer getSummoner2Casts() {
        return summoner2Casts;
    }

    public void setSummoner2Casts(Integer summoner2Casts) {
        this.summoner2Casts = summoner2Casts;
    }

    public Integer getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(Integer goldEarned) {
        this.goldEarned = goldEarned;
    }

    public Integer getNexusLost() {
        return nexusLost;
    }

    public void setNexusLost(Integer nexusLost) {
        this.nexusLost = nexusLost;
    }

    public Integer getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }

    public void setPhysicalDamageTaken(Integer physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }

    public Integer getChampLvl() {
        return champLvl;
    }

    public void setChampLvl(Integer champLvl) {
        this.champLvl = champLvl;
    }

    public Integer getTotalDamageTaken() {
        return totalDamageTaken;
    }

    public void setTotalDamageTaken(Integer totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }

    public Integer getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    public void setNeutralMinionsKilled(Integer neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }

    public Integer getChampionTransform() {
        return championTransform;
    }

    public void setChampionTransform(Integer championTransform) {
        this.championTransform = championTransform;
    }

    public Integer getTripleKills() {
        return tripleKills;
    }

    public void setTripleKills(Integer tripleKills) {
        this.tripleKills = tripleKills;
    }

    public Integer getDamageSelfMitigated() {
        return damageSelfMitigated;
    }

    public void setDamageSelfMitigated(Integer damageSelfMitigated) {
        this.damageSelfMitigated = damageSelfMitigated;
    }

    public Integer getInhibitorsLost() {
        return inhibitorsLost;
    }

    public void setInhibitorsLost(Integer inhibitorsLost) {
        this.inhibitorsLost = inhibitorsLost;
    }

    public Integer getInhibitorTakedowns() {
        return inhibitorTakedowns;
    }

    public void setInhibitorTakedowns(Integer inhibitorTakedowns) {
        this.inhibitorTakedowns = inhibitorTakedowns;
    }

    public Integer getLargestCriticalStrike() {
        return largestCriticalStrike;
    }

    public void setLargestCriticalStrike(Integer largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }

    public Integer getTotalHealsOnTeammates() {
        return totalHealsOnTeammates;
    }

    public void setTotalHealsOnTeammates(Integer totalHealsOnTeammates) {
        this.totalHealsOnTeammates = totalHealsOnTeammates;
    }

    public Integer getSummoner1Casts() {
        return summoner1Casts;
    }

    public void setSummoner1Casts(Integer summoner1Casts) {
        this.summoner1Casts = summoner1Casts;
    }

    public Integer getDamageDealtsToBuildings() {
        return damageDealtsToBuildings;
    }

    public void setDamageDealtsToBuildings(Integer damageDealtsToBuildings) {
        this.damageDealtsToBuildings = damageDealtsToBuildings;
    }

    public Integer getMagicDamageDealt() {
        return magicDamageDealt;
    }

    public void setMagicDamageDealt(Integer magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }

    public Integer getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Integer timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public Integer getTimeCCingOthers() {
        return timeCCingOthers;
    }

    public void setTimeCCingOthers(Integer timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }

    public Integer getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }

    public void setPhysicalDamageDealtToChampions(Integer physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }

    public Integer getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(Integer totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public Integer getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(Integer visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public Integer getKills() {
        return Kills;
    }

    public void setKills(Integer kills) {
        Kills = kills;
    }

    public Boolean getFirstTowerAssist() {
        return firstTowerAssist;
    }

    public void setFirstTowerAssist(Boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    public Integer getTurretKills() {
        return turretKills;
    }

    public void setTurretKills(Integer turretKills) {
        this.turretKills = turretKills;
    }

    public Boolean getFirstBloodAssist() {
        return firstBloodAssist;
    }

    public void setFirstBloodAssist(Boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    public Integer getTrueDamageTaken() {
        return trueDamageTaken;
    }

    public void setTrueDamageTaken(Integer trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(Integer itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    public Integer getObjectivesStolenAssists() {
        return objectivesStolenAssists;
    }

    public void setObjectivesStolenAssists(Integer objectivesStolenAssists) {
        this.objectivesStolenAssists = objectivesStolenAssists;
    }

    public Integer getDamageDealtsToTurrets() {
        return damageDealtsToTurrets;
    }

    public void setDamageDealtsToTurrets(Integer damageDealtsToTurrets) {
        this.damageDealtsToTurrets = damageDealtsToTurrets;
    }

    public Integer getTotalHeal() {
        return totalHeal;
    }

    public void setTotalHeal(Integer totalHeal) {
        this.totalHeal = totalHeal;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public Boolean getGameEndedInSurrender() {
        return gameEndedInSurrender;
    }

    public void setGameEndedInSurrender(Boolean gameEndedInSurrender) {
        this.gameEndedInSurrender = gameEndedInSurrender;
    }

    public Integer getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }

    public void setPhysicalDamageDealt(Integer physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }

    public Integer getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }

    public void setTrueDamageDealtToChampions(Integer trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }

    public Integer getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(Integer dragonKills) {
        this.dragonKills = dragonKills;
    }

    public Integer getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(Integer baronKills) {
        this.baronKills = baronKills;
    }

    public Integer getDoubleKills() {
        return doubleKills;
    }

    public void setDoubleKills(Integer doubleKills) {
        this.doubleKills = doubleKills;
    }

    public Integer getNexusKills() {
        return nexusKills;
    }

    public void setNexusKills(Integer nexusKills) {
        this.nexusKills = nexusKills;
    }

    public Integer getTrueDamageDealt() {
        return trueDamageDealt;
    }

    public void setTrueDamageDealt(Integer trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }

    public Integer getSpell1Casts() {
        return spell1Casts;
    }

    public void setSpell1Casts(Integer spell1Casts) {
        this.spell1Casts = spell1Casts;
    }

    public Boolean getGameEndedInEarlySurrender() {
        return gameEndedInEarlySurrender;
    }

    public void setGameEndedInEarlySurrender(Boolean gameEndedInEarlySurrender) {
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
    }

    public Integer getSpell4Casts() {
        return spell4Casts;
    }

    public void setSpell4Casts(Integer spell4Casts) {
        this.spell4Casts = spell4Casts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchParticipant that = (MatchParticipant) o;
        return Objects.equals(matchParticipantKey, that.matchParticipantKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantKey);
    }

    @Override
    public String toString() {
        return "MatchParticipant{}";
    }
}
