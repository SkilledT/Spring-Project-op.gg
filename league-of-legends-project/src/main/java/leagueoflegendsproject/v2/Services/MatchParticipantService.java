package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.*;
import leagueoflegendsproject.v2.Repositories.MatchParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchParticipantService {
    private final MatchParticipantRepository matchParticipantRepository;

    public MatchParticipant createMatchParticipant(Boolean win,
                                                   Integer bountyLevel,
                                                   Integer totalUnitsHealed,
                                                   Integer largestMultiKill,
                                                   Integer spell2Cast,
                                                   Integer champExperience,
                                                   Integer turretTakedowns,
                                                   Integer damageDealtToObjectives,
                                                   Integer magicDamageTaken,
                                                   Integer deaths,
                                                   Integer objectivesStolen,
                                                   Integer detectorWardsPlaced,
                                                   Integer magicDamageDealtToChampions,
                                                   Integer wardsKilled,
                                                   Integer pentakills,
                                                   Integer spell3Casts,
                                                   Boolean firstTowerKill,
                                                   String individualPosition,
                                                   Integer wardsPlaced,
                                                   Integer totalDamageDealt,
                                                   Integer largestKillingSpree,
                                                   Integer totalDamageDealtToChampions,
                                                   Integer summoner2Id,
                                                   String role,
                                                   Integer totalTimeSpentDead,
                                                   Integer InhibitorKills,
                                                   Integer totalTimeCcDealt,
                                                   Integer participantId,
                                                   Boolean teamEarlySurrender,
                                                   Integer goldSpent,
                                                   Integer unrealKills,
                                                   Integer consumablesPurchased,
                                                   Integer visionScore,
                                                   Boolean firstBloodKill,
                                                   Integer longestTimeSpentLiving,
                                                   Integer sightWardsBoughtInGame,
                                                   Integer turretLost,
                                                   Integer quadrakills,
                                                   Integer nexusTakedowns,
                                                   Integer summoner1Id,
                                                   Integer totalDamageShieldedOnTeammates,
                                                   Integer summoner2Casts,
                                                   Integer goldEarned,
                                                   Integer nexusLost,
                                                   Integer physicalDamageTaken,
                                                   Integer champLvl,
                                                   Integer totalDamageTaken,
                                                   Integer neutralMinionsKilled,
                                                   Integer championTransform,
                                                   Integer tripleKills,
                                                   Integer damageSelfMitigated,
                                                   Integer inhibitorsLost,
                                                   Integer inhibitorTakedowns,
                                                   Integer largestCriticalStrike,
                                                   Integer totalHealsOnTeammates,
                                                   Integer summoner1Casts,
                                                   Integer damageDealtsToBuildings,
                                                   Integer magicDamageDealt,
                                                   Integer timePlayed,
                                                   String championName,
                                                   Integer timeCCingOthers,
                                                   String teamPosition,
                                                   Integer physicalDamageDealtToChampions,
                                                   Integer totalMinionsKilled,
                                                   Integer visionWardsBoughtInGame,
                                                   Integer kills,
                                                   Boolean firstTowerAssist,
                                                   Integer turretKills,
                                                   Boolean firstBloodAssist,
                                                   Integer trueDamageTaken,
                                                   Integer assists,
                                                   Integer itemsPurchased,
                                                   Integer objectivesStolenAssists,
                                                   Integer damageDealtsToTurrets,
                                                   Integer totalHeal,
                                                   String lane,
                                                   Boolean gameEndedInSurrender,
                                                   Integer physicalDamageDealt,
                                                   Integer trueDamageDealtToChampions,
                                                   Integer dragonKills,
                                                   Integer baronKills,
                                                   Integer doubleKills,
                                                   Integer nexusKills,
                                                   Integer trueDamageDealt,
                                                   Integer spell1Casts,
                                                   Boolean gameEndedInEarlySurrender,
                                                   Integer spell4Casts,
                                                   Double killParticipation,
                                                   Match match,
                                                   Team team,
                                                   ChampionSnapshot championSnapshot,
                                                   SummonerSnapshot summoner) {
        return new MatchParticipant()
                .setWin(win)
                .setBountyLevel(bountyLevel)
                .setTotalUnitsHealed(totalUnitsHealed)
                .setLargestMultiKill(largestMultiKill)
                .setSpell2Cast(spell2Cast)
                .setChampExperience(champExperience)
                .setTurretTakedowns(turretTakedowns)
                .setDamageDealtToObjectives(damageDealtToObjectives)
                .setMagicDamageTaken(magicDamageTaken)
                .setDeaths(deaths)
                .setObjectivesStolen(objectivesStolen)
                .setDetectorWardsPlaced(detectorWardsPlaced)
                .setMagicDamageDealtToChampions(magicDamageDealtToChampions)
                .setWardsKilled(wardsKilled)
                .setPentakills(pentakills)
                .setSpell3Casts(spell3Casts)
                .setFirstTowerKill(firstTowerKill)
                .setIndividualPosition(individualPosition)
                .setWardsPlaced(wardsPlaced)
                .setTotalDamageDealt(totalDamageDealt)
                .setLargestKillingSpree(largestKillingSpree)
                .setTotalDamageDealtToChampions(totalDamageDealtToChampions)
                .setSummoner2Id(summoner2Id)
                .setRole(role)
                .setTotalTimeSpentDead(totalTimeSpentDead)
                .setInhibitorKills(InhibitorKills)
                .setTotalTimeCcDealt(totalTimeCcDealt)
                .setParticipantId(participantId)
                .setTeamEarlySurrender(teamEarlySurrender)
                .setGoldSpent(goldSpent)
                .setUnrealKills(unrealKills)
                .setConsumablesPurchased(consumablesPurchased)
                .setVisionScore(visionScore)
                .setFirstBloodKill(firstBloodKill)
                .setLongestTimeSpentLiving(longestTimeSpentLiving)
                .setSightWardsBoughtInGame(sightWardsBoughtInGame)
                .setTurretLost(turretLost)
                .setQuadrakills(quadrakills)
                .setNexusTakedowns(nexusTakedowns)
                .setSummoner1Id(summoner1Id)
                .setTotalDamageShieldedOnTeammates(totalDamageShieldedOnTeammates)
                .setSummoner2Casts(summoner2Casts)
                .setGoldEarned(goldEarned)
                .setNexusLost(nexusLost)
                .setPhysicalDamageTaken(physicalDamageTaken)
                .setChampLvl(champLvl)
                .setTotalDamageTaken(totalDamageTaken)
                .setNeutralMinionsKilled(neutralMinionsKilled)
                .setChampionTransform(championTransform)
                .setTripleKills(tripleKills)
                .setDamageSelfMitigated(damageSelfMitigated)
                .setInhibitorsLost(inhibitorsLost)
                .setInhibitorTakedowns(inhibitorTakedowns)
                .setLargestCriticalStrike(largestCriticalStrike)
                .setTotalHealsOnTeammates(totalHealsOnTeammates)
                .setSummoner1Casts(summoner1Casts)
                .setDamageDealtsToBuildings(damageDealtsToBuildings)
                .setMagicDamageDealt(magicDamageDealt)
                .setTimePlayed(timePlayed)
                .setChampionName(championName)
                .setTimeCCingOthers(timeCCingOthers)
                .setTeamPosition(teamPosition)
                .setPhysicalDamageDealtToChampions(physicalDamageDealtToChampions)
                .setTotalMinionsKilled(totalMinionsKilled)
                .setVisionWardsBoughtInGame(visionWardsBoughtInGame)
                .setKills(kills)
                .setFirstTowerAssist(firstTowerAssist)
                .setTurretKills(turretKills)
                .setFirstBloodAssist(firstBloodAssist)
                .setTrueDamageTaken(trueDamageTaken)
                .setAssists(assists)
                .setItemsPurchased(itemsPurchased)
                .setObjectivesStolenAssists(objectivesStolenAssists)
                .setDamageDealtsToTurrets(damageDealtsToTurrets)
                .setTotalHeal(totalHeal)
                .setLane(lane)
                .setGameEndedInSurrender(gameEndedInSurrender)
                .setPhysicalDamageDealt(physicalDamageDealt)
                .setTrueDamageDealtToChampions(trueDamageDealtToChampions)
                .setDragonKills(dragonKills)
                .setBaronKills(baronKills)
                .setDoubleKills(doubleKills)
                .setNexusKills(nexusKills)
                .setTrueDamageDealt(trueDamageDealt)
                .setSpell1Casts(spell1Casts)
                .setGameEndedInEarlySurrender(gameEndedInEarlySurrender)
                .setSpell4Casts(spell4Casts)
                .setKillParticipation(killParticipation)
                .setMatch(match)
                .setTeam(team)
                .setChampionSnapshot(championSnapshot)
                .setSummonerSnapshot(summoner);
    }

    public MatchParticipant saveMatchParticipant(MatchParticipant participant) {
        return matchParticipantRepository.save(participant);
    }
}



























































































