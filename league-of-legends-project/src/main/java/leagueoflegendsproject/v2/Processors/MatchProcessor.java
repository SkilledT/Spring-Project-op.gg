package leagueoflegendsproject.v2.Processors;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services.IntegrationMatchService;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.Match;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.SelectionsItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId.TeamsItem;
import leagueoflegendsproject.v2.Models.*;
import leagueoflegendsproject.v2.Processors.Creators.TeamProcessorCreator;
import leagueoflegendsproject.v2.Services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MatchProcessor {
    private final IntegrationMatchService httpMatchService;
    private final MatchService matchService;
    private final ItemService itemService;
    private final SummonerService summonerService;
    private final PerkService perkService;
    private final ChampionSnapshotService championService;
    private final SummonerSnapshotService summonerSnapshotService;
    private final TeamProcessorCreator teamProcessorCreator;
    private final PatchVersionService patchVersionService;
    private final MatchParticipantService matchParticipantService;
    private final MatchParticipantItemService matchParticipantItemService;
    private final MatchParticipantPerkService matchParticipantPerkService;

    public void fetchSummonerMatches(String summonerPuuid) {
        log.info("============ START FETCHING MATCHES FOR SUMMONER WITH PUUID {}", summonerPuuid);
        List<Match> apiMatches = httpMatchService.getMatchCollectionByNicknameSyncByPuuid(summonerPuuid);
        for (var apiMatch : apiMatches) {
            var dbMatch = matchService.findByMatchId(apiMatch.getMetadata().getMatchId());
            if (dbMatch.isPresent()) {
                log.debug("Match with id {} for a summer with puuid {} already exist, will not be processed",
                        apiMatch.getMetadata().getMatchId(), summonerPuuid);
            }
            Optional<PatchVersion> version = patchVersionService.findNewestPatchVersion(apiMatch.getInfo().getGameStartTimestamp());

            if (version.isEmpty()) {
                log.error("No current version has been found for match with id {} ", apiMatch.getMetadata().getMatchId());
                return;
            }

            var apiParticipants = apiMatch.getInfo().getParticipants();
            Map<String, SummonerSnapshot> summonerSnapshotsBySummonerId = createSummonerSnapshotsAndGetBySummonerId(apiParticipants);
            Map<String, ChampionSnapshot> championSnapshotMap = getCurrentChampionSnapshots(apiParticipants, version.get().getVersion());
            Map<Integer, Item> itemMap = getItemsByExternalIdAndVersion(apiParticipants, version.get().getVersion());
            Map<Integer, Perk> perkMap = getPerkByExternalIdAndVersion(apiParticipants, version.get().getVersion());

            var createdDbMatch = createMatch(apiMatch, version.get().getVersion());
            Map<Integer, Team> teamMap = createAndGetTeamByExternalId(apiMatch.getInfo().getTeams(), createdDbMatch);

            for (var participantsItem : apiMatch.getInfo().getParticipants()) {
                MatchParticipant createDbMatchParticipant = createMatchParticipant(participantsItem, createdDbMatch, teamMap,
                        championSnapshotMap, summonerSnapshotsBySummonerId);
                createParticipantItems(participantsItem, itemMap, createDbMatchParticipant, version.get().getVersion());
                createParticipantPerk(participantsItem, perkMap, createDbMatchParticipant, version.get().getVersion());
            }

        }
        log.info("============ FINISH FETCHING MATCHES FOR SUMMONER WITH PUUID {}", summonerPuuid);
    }

    private void createParticipantPerk(ParticipantsItem participantsItem, Map<Integer, Perk> perkMap,
                                       MatchParticipant matchParticipant, String patchVersion) {
        var styles = participantsItem.getPerks().getStyles();
        for (var style: styles) {
            var type = matchParticipantPerkService.resolveType(style.getDescription());
            for (var selection: style.getSelections()) {
                var perk = perkMap.get(selection.getPerk());
                if (perk == null) {
                    // TODO: ADD ENTRY IN AUDIT ENTITY TO RE-FETCH WHEN AVAILABLE
                    log.error("Perk with id {} for patch version {} has not been found",
                            selection.getPerk(), patchVersion);

                }
                matchParticipantPerkService.createAndSaveMatchParticipantPerk(
                        perk, matchParticipant, type
                );
            }
        }
    }

    private void createParticipantItems(ParticipantsItem participantsItem, Map<Integer, Item> itemMap,
                                        MatchParticipant matchParticipant, String patchVersion) {
        log.debug("Creating items for MatchParticipation with ID {} for a patchVersion {}",
                matchParticipant.getParticipantId(), patchVersion);
        var itemsIds = List.of(
                participantsItem.getItem0(), participantsItem.getItem1(), participantsItem.getItem2(),
                participantsItem.getItem3(), participantsItem.getItem4(), participantsItem.getItem5(),
                participantsItem.getItem6()
        );

        for (int position = 0; position < itemsIds.size(); position++) {
            var itemId = itemsIds.get(position);
            var item = itemMap.get(itemId);
            if (item == null) {
                // TODO: TO CREATE LOG ENTITY AND RE-FETCH MECHANISM
                log.error("Cannot find an item with id {} for a patch {}", itemId, patchVersion);
                continue;
            }
            matchParticipantItemService.createAndSaveMatchParticipant(position, item, matchParticipant);
        }
        log.debug("COMPLETED creating items for MatchParticipation with ID {} for a patchVersion {}",
                matchParticipant.getParticipantId(), patchVersion);
    }

    private MatchParticipant createMatchParticipant(ParticipantsItem participantsItem,
                                                    leagueoflegendsproject.v2.Models.Match match,
                                                    Map<Integer, Team> teamMap,
                                                    Map<String, ChampionSnapshot> championSnapshotMap,
                                                    Map<String, SummonerSnapshot> summonerSnapshotsBySummonerId) {
        Team team = teamMap.get(participantsItem.getTeamId());
        ChampionSnapshot championSnapshot = championSnapshotMap.get(String.valueOf(participantsItem.getChampionId()));
        SummonerSnapshot summonerSnapshot = summonerSnapshotsBySummonerId.get(participantsItem.getSummonerId());
        var participant = matchParticipantService.createMatchParticipant(
                participantsItem.isWin(),
                participantsItem.getBountyLevel(),
                participantsItem.getTotalUnitsHealed(),
                participantsItem.getLargestMultiKill(),
                participantsItem.getSpell2Casts(),
                participantsItem.getChampExperience(),
                participantsItem.getTurretTakedowns(),
                participantsItem.getDamageDealtToObjectives(),
                participantsItem.getMagicDamageTaken(),
                participantsItem.getDeaths(),
                participantsItem.getObjectivesStolen(),
                participantsItem.getDetectorWardsPlaced(),
                participantsItem.getMagicDamageDealtToChampions(),
                participantsItem.getWardsKilled(),
                participantsItem.getPentaKills(),
                participantsItem.getSpell3Casts(),
                participantsItem.isFirstTowerKill(),
                participantsItem.getIndividualPosition(),
                participantsItem.getWardsPlaced(),
                participantsItem.getTotalDamageDealt(),
                participantsItem.getLargestKillingSpree(),
                participantsItem.getTotalDamageDealtToChampions(),
                participantsItem.getSummoner2Id(),
                participantsItem.getRole(),
                participantsItem.getTotalTimeSpentDead(),
                participantsItem.getInhibitorKills(),
                participantsItem.getTotalTimeCCDealt(),
                participantsItem.getParticipantId(),
                participantsItem.isTeamEarlySurrendered(),
                participantsItem.getGoldSpent(),
                participantsItem.getUnrealKills(),
                participantsItem.getConsumablesPurchased(),
                participantsItem.getVisionScore(),
                participantsItem.isFirstBloodKill(),
                participantsItem.getLongestTimeSpentLiving(),
                participantsItem.getSightWardsBoughtInGame(),
                participantsItem.getTurretsLost(),
                participantsItem.getQuadraKills(),
                participantsItem.getNexusTakedowns(),
                participantsItem.getSummoner1Id(),
                participantsItem.getTotalDamageShieldedOnTeammates(),
                participantsItem.getSummoner2Casts(),
                participantsItem.getGoldEarned(),
                participantsItem.getNexusLost(),
                participantsItem.getPhysicalDamageTaken(),
                participantsItem.getChampLevel(),
                participantsItem.getTotalDamageTaken(),
                participantsItem.getNeutralMinionsKilled(),
                participantsItem.getChampionTransform(),
                participantsItem.getTripleKills(),
                participantsItem.getDamageSelfMitigated(),
                participantsItem.getInhibitorsLost(),
                participantsItem.getInhibitorTakedowns(),
                participantsItem.getLargestCriticalStrike(),
                participantsItem.getTotalHealsOnTeammates(),
                participantsItem.getSummoner1Casts(),
                participantsItem.getDamageDealtToBuildings(),
                participantsItem.getMagicDamageDealt(),
                participantsItem.getTimePlayed(),
                participantsItem.getChampionName(),
                participantsItem.getTimeCCingOthers(),
                participantsItem.getTeamPosition(),
                participantsItem.getPhysicalDamageDealtToChampions(),
                participantsItem.getTotalMinionsKilled(),
                participantsItem.getVisionWardsBoughtInGame(),
                participantsItem.getKills(),
                participantsItem.isFirstTowerAssist(),
                participantsItem.getTurretKills(),
                participantsItem.isFirstBloodAssist(),
                participantsItem.getTrueDamageTaken(),
                participantsItem.getAssists(),
                participantsItem.getItemsPurchased(),
                participantsItem.getObjectivesStolenAssists(),
                participantsItem.getDamageDealtToTurrets(),
                participantsItem.getTotalHeal(),
                participantsItem.getLane(),
                participantsItem.isGameEndedInSurrender(),
                participantsItem.getPhysicalDamageDealt(),
                participantsItem.getTrueDamageDealtToChampions(),
                participantsItem.getDragonKills(),
                participantsItem.getBaronKills(),
                participantsItem.getDoubleKills(),
                participantsItem.getNexusKills(),
                participantsItem.getTrueDamageDealt(),
                participantsItem.getSpell1Casts(),
                participantsItem.isGameEndedInEarlySurrender(),
                participantsItem.getSpell4Casts(),
                null, // Kill participation -> to remove,
                match,
                team,
                championSnapshot,
                summonerSnapshot
        );

        match.getTeams().add(team);
        match.getMatchParticipantSet().add(participant);
        team.setMatch(match);
        team.getMatchParticipants().add(participant);
        championSnapshot.getMatchParticipants().add(participant);
        summonerSnapshot.getMatchParticipant().add(participant);
        matchParticipantService.saveMatchParticipant(participant);

        return participant;
    }

    private leagueoflegendsproject.v2.Models.Match createMatch(Match apiMatch, String apiVersion) {
        return matchService.createMatch(
                apiMatch.getMetadata().getMatchId(),
                Integer.valueOf(apiMatch.getMetadata().getMatchId()),
                apiMatch.getInfo().getGameType(),
                apiMatch.getInfo().getQueueId(),
                apiMatch.getInfo().getGameDuration(),
                (int) apiMatch.getInfo().getGameStartTimestamp(),
                apiMatch.getInfo().getPlatformId(),
                apiMatch.getInfo().getGameCreation(),
                apiMatch.getInfo().getMapId(),
                apiMatch.getInfo().getGameMode(),
                apiVersion
        );
    }

    private Map<String, SummonerSnapshot> createSummonerSnapshotsAndGetBySummonerId(
            List<ParticipantsItem> participantsItems) {

        return participantsItems.stream()
                .map(summonerItem -> {
                    var dbSummonerOptional = summonerService.findSummonerBySummonerId(summonerItem.getSummonerId());
                    return dbSummonerOptional
                            .orElseGet(() ->
                                    summonerService.createAndSaveUnrankedSummoner(
                                            summonerItem.getSummonerId(),
                                            summonerItem.getSummonerName()));
                })
                .collect(Collectors.toMap(Summoner::getSummonerId,
                        x -> summonerSnapshotService.findNewestSummonerSnapshot(x).get()));
    }

    private Map<String, ChampionSnapshot> getCurrentChampionSnapshots(
            List<ParticipantsItem> participantsItems, String version) {
        var externalChampionIds = participantsItems.stream().map(ParticipantsItem::getChampionId)
                .map(String::valueOf)
                .collect(Collectors.toList());
        return championService.findByExternalIdAndVersion(externalChampionIds, version)
                .stream()
                .collect(Collectors.toMap(ChampionSnapshot::getExternalId, x -> x));
    }

    private Map<Integer, Team> createAndGetTeamByExternalId(
            List<TeamsItem> teamsItems, leagueoflegendsproject.v2.Models.Match match) {
        return teamProcessorCreator.createTeamsAndGetByTeamExternalId(teamsItems, match);
    }

    private Map<Integer, Item> getItemsByExternalIdAndVersion(List<ParticipantsItem> participantsItems, String version) {
        Set<Integer> itemIds = new HashSet<>();
        for (var participantItem : participantsItems) {
            itemIds.add(participantItem.getItem0());
            itemIds.add(participantItem.getItem1());
            itemIds.add(participantItem.getItem2());
            itemIds.add(participantItem.getItem3());
            itemIds.add(participantItem.getItem4());
            itemIds.add(participantItem.getItem5());
            itemIds.add(participantItem.getItem6());
        }
        return itemService.getByExternalIdsAndVersion(new ArrayList<>(itemIds), version)
                .stream()
                .collect(Collectors.toMap(Item::getExternalId, e -> e));
    }

    private Map<Integer, Perk> getPerkByExternalIdAndVersion(List<ParticipantsItem> participantsItems, String version) {
        List<Integer> itemIds = new ArrayList<>(participantsItems
                .stream()
                .flatMap(e -> e.getPerks().getStyles().stream())
                .flatMap(e -> e.getSelections().stream())
                .map(SelectionsItem::getPerk)
                .collect(Collectors.toUnmodifiableSet()));
        return perkService.findByExternalIdsAndVersion(itemIds, version)
                .stream()
                .collect(Collectors.toMap(
                        Perk::getExternalId,
                        perk -> perk
                ));
    }
}
