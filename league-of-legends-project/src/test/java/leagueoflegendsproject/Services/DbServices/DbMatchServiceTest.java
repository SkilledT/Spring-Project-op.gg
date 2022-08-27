package leagueoflegendsproject.Services.DbServices;

import com.google.gson.reflect.TypeToken;
import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;
import leagueoflegendsproject.DTOs.PreferedRoleDto;
import leagueoflegendsproject.Helpers.FileUtils;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Helpers.TestUtils.MatchParticipantBuilder;
import leagueoflegendsproject.Helpers.TestUtils.PlayersChampionStatsDtoBuilder;
import leagueoflegendsproject.Helpers.TestUtils.PreferedRoleDtoBuilder;
import leagueoflegendsproject.Models.Database.*;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.Keys.MatchTeamKey;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeague;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import leagueoflegendsproject.Strategies.RoleStrategies.PerformanceStrategyFactory;
import leagueoflegendsproject.Utils.MatchParticipantUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class DbMatchServiceTest {

    private SummonerRepository mockSummonerRepository;
    private ItemRepository mockItemRepository;
    private TeamRepository mockTeamRepository;
    private MatchRepository mockMatchRepository;
    private MatchParticipantRepository mockMatchParticipantRepository;
    private MatchTeamRepository mockMatchTeamRepository;
    private ChampionRepository mockChampionRepository;
    private ObjectiveRepository mockObjectiveRepository;
    private PerkRepository mockPerkRepository;
    private HttpSummonerService mockHttpSummonerService;


    @Autowired private SummonerRepository summonerRepository;
    @Autowired private ItemRepository itemRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private MatchRepository matchRepository;
    @Autowired private ParticipantItemsRepository participantItemsRepository;
    @Autowired private MatchParticipantRepository matchParticipantRepository;
    @Autowired private BanRepository banRepository;
    @Autowired private MatchTeamRepository matchTeamRepository;
    @Autowired private ChampionRepository championRepository;
    @Autowired private TeamObjectiveRepository teamObjectiveRepository;
    @Autowired private ObjectiveRepository objectiveRepository;
    @Autowired private MatchParticipantPerkRepository matchParticipantPerkRepository;
    @Autowired private PerkRepository perkRepository;
    @Autowired private HttpSummonerService httpSummonerService;
    @Autowired private PerformanceStrategyFactory performanceStrategyFactory;
    @Autowired private MatchParticipantUtils matchParticipantUtils;

    @BeforeEach
    void setUp() throws IOException {
        List<Perk> perks = FileUtils.parseFileToObject(
                Objects.requireNonNull(getClass().getClassLoader().getResource("perks_response.csv")).getPath(),
                new TypeToken<>() {
                }
        );
        List<Item> items = FileUtils.parseFileToObject(
                Objects.requireNonNull(getClass().getClassLoader().getResource("items_response.csv")).getPath(),
                new TypeToken<>() {
                }
        );
        List<Champion> champions = FileUtils.parseFileToObject(
                Objects.requireNonNull(getClass().getClassLoader().getResource("champions_response.csv")).getPath(),
                new TypeToken<>() {
                }
        );
        perkRepository.saveAllAndFlush(perks);
        itemRepository.saveAllAndFlush(items);
        championRepository.saveAllAndFlush(champions);

        this.mockSummonerRepository = mock(SummonerRepository.class);
        this.mockItemRepository = mock(ItemRepository.class);
        this.mockTeamRepository = mock(TeamRepository.class);
        this.mockMatchRepository = mock(MatchRepository.class);
        this.mockMatchParticipantRepository = mock(MatchParticipantRepository.class);
        this.mockMatchTeamRepository = mock(MatchTeamRepository.class);
        this.mockChampionRepository = mock(ChampionRepository.class);
        this.mockObjectiveRepository = mock(ObjectiveRepository.class);
        this.mockPerkRepository = mock(PerkRepository.class);
        this.mockHttpSummonerService = mock(HttpSummonerService.class);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addMatchToDb_shouldAddMatchObjectAndAllRelated() throws IOException {
        //given
        String matchResponsePath = Objects.requireNonNull(getClass().getClassLoader().getResource("matchResponse.json")).getPath();
        leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match = FileUtils.parseFileToObject(matchResponsePath, Match.class);
        Set<Integer> teamIds = match.getInfo().getParticipants().stream().map(ParticipantsItem::getTeamId).collect(Collectors.toSet());

        String summonerResponsesPath = Objects.requireNonNull(getClass().getClassLoader().getResource("ApiSummoners/SummonerList.json")).getPath();;
        SummonerLeagueResponseItem[][] summonerLeague = FileUtils.parseFileToObject(summonerResponsesPath, SummonerLeagueResponseItem[][].class);
        List<SummonerLeagueResponseItem> summonerLeagueResponseItems = new ArrayList<>();

        for (SummonerLeagueResponseItem[] items : summonerLeague) {
            for (SummonerLeagueResponseItem item : items) {
                if(item.getQueueType().equals("RANKED_SOLO_5x5")) {
                    summonerLeagueResponseItems.add(item);
                }
            }
        }

        List<Summoner> summoners = summonerLeagueResponseItems.stream().map(Summoner::new).collect(Collectors.toList());
        Map<String, Summoner> summonerMapByName = summoners.stream().collect(Collectors.toMap(Summoner::getSummonerNickname, x -> x));

        // when
        match.getInfo().getParticipants().forEach(participantsItem -> {
            when(mockHttpSummonerService.fetchSummonerHTTP(participantsItem.getSummonerName())).thenReturn(
                    summonerMapByName.get(participantsItem.getSummonerName())
            );
        });
        var toTest = new DbMatchService(summonerRepository, itemRepository, teamRepository, matchRepository,
                matchTeamRepository, matchParticipantRepository, objectiveRepository, perkRepository,
                mockHttpSummonerService, championRepository, performanceStrategyFactory, matchParticipantUtils);
        toTest.addMatchToDb(match);

        // then
        assertNotEquals(matchRepository.findById(match.getMetadata().getMatchId()), null, "Related match record should be created");
        match.getInfo().getParticipants().forEach(participantsItem ->
                assertNotEquals(summonerRepository.findSummonerBySummonerNickname(participantsItem.getSummonerName()), null, "Related summoner record should be created")
        );
        match.getInfo().getParticipants().forEach(participant -> {
            var actual = matchParticipantRepository.findById(new MatchParticipantKey(participant.getSummonerId(), match.getMetadata().getMatchId()));
            assertTrue(actual.isPresent(), "There is not such a match participant in DB, summoner ID: " + participant.getSummonerId() + ", match ID: " + match.getMetadata().getMatchId());
        });
        teamIds.forEach(teamId -> {
            var result = matchTeamRepository.findById(new MatchTeamKey(match.getMetadata().getMatchId(), teamId));
            assertTrue(result.isPresent(), "Match team record should be created with, matchId: " + match.getMetadata().getMatchId() + ", and teamId: " + teamId);
            assertEquals(result.get().getMatch().getMatchId(), match.getMetadata().getMatchId(), "Match Id should be " + match.getMetadata().getMatchId());
        });
        //assertEquals(10, banRepository.findAll().size(), "There should be 10 new-created records of ban items (1 per person)");
        assertEquals(12, teamObjectiveRepository.findAll().size(), "There should be 12 new-created records of objectives items (6 per team)");
        // 6 perks per person so 6x10 = 60
        assertEquals(60, matchParticipantPerkRepository.findAll().size(), "There should be 60 new-created records of participant perks (6 per person)");
        var matchTeamMap = teamObjectiveRepository.findAll().stream().collect(groupingBy(e -> e.getMatchTeam().getTeam().getId(), Collectors.toSet()));
        for (Integer key : matchTeamMap.keySet()) {
            assertEquals(6, matchTeamMap.get(key).size(), "There should be 6 team objectives per team");
        }
        teamIds.forEach(teamId -> {
            var expectedNumberOfRecords = 6;
            var actualNumberOfRecords = (int) teamObjectiveRepository.findAll().stream().filter(obj -> obj.getMatchTeam().getTeam().getId().equals(teamId) && obj.getMatchTeam().getMatch().getMatchId().equals(match.getMetadata().getMatchId())).count();
            assertEquals(expectedNumberOfRecords, actualNumberOfRecords, "Every team should have 6 objective records per match");
        });
        matchParticipantRepository.findAll().forEach(mp -> {
            assertNotEquals(null, mp.getChampion(), "There should be one champion related to Match Participant");
        });
        var itemsByMatchParticipantSummonerNickname = participantItemsRepository.findAll().stream().collect(groupingBy(e -> e.getMatchParticipant().getSummoner().getSummonerNickname(), Collectors.toSet()));
        assertEquals(10, itemsByMatchParticipantSummonerNickname.keySet().size(), "There should be 10 players created from response");
        itemsByMatchParticipantSummonerNickname.keySet().forEach(key -> {
            var items = itemsByMatchParticipantSummonerNickname.get(key);
            assertEquals(6, items.size(), "There always should be 6 items per match participant");
        });
        assertEquals(60, participantItemsRepository.findAll().size(), "There should be 60 new-created records of participant items (6 per person)");
    }

    @Test
    void getChampionStatsByNickname_shouldReturnSetOfPlayersChampionStatsDto() {
        // Given
        Constants.MatchParticipantConstants.IndividualPosition position = Constants.MatchParticipantConstants.IndividualPosition.UTILITY;
        int championId = 1000;
        String championName = "Akali";
        MatchParticipant matchParticipant1 = new MatchParticipantBuilder()
                .withIndividualPosition(position)
                .isWon(false)
                .withChampionName(championName, championId)
                .withKills(3)
                .withDeaths(3)
                .withAssists(3)
                .withTotalMinionsKilled(50)
                .build();
        MatchParticipant matchParticipant2 = new MatchParticipantBuilder()
                .withIndividualPosition(position)
                .isWon(true)
                .withChampionName(championName, championId)
                .withKills(5)
                .withDeaths(5)
                .withAssists(5)
                .withTotalMinionsKilled(100)
                .build();
        List<MatchParticipant> matchParticipantList = List.of(matchParticipant1, matchParticipant2);
        PlayersChampionStatsDto expectedPlayersChampionStatsDto = new PlayersChampionStatsDtoBuilder()
                .withWinRatio(0.5d)
                .withChampName(championName)
                .withCS(75)
                .withAvgKills(4)
                .withAvgAssists(4)
                .withAvgDeaths(4)
                .withPlayedMatches(2)
                .withKDA(2.0)
                .build();

        Set<PlayersChampionStatsDto> expectedResult = Set.of(expectedPlayersChampionStatsDto);

        // When
        when(mockMatchParticipantRepository.findBySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(anyString()))
                .thenReturn(matchParticipantList);
        var toTest = new DbMatchService(mockSummonerRepository, mockItemRepository, mockTeamRepository, mockMatchRepository,
                mockMatchTeamRepository, mockMatchParticipantRepository, mockObjectiveRepository, mockPerkRepository,
                mockHttpSummonerService, mockChampionRepository, performanceStrategyFactory, matchParticipantUtils);
        var actualResult = toTest.getChampionStatsByNickname("Skilled Teaser");

        // Then
        assertEquals(expectedResult.size(), actualResult.size(), "size is not equals");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPreferredRole_shouldReturnDTOList() {
        // Given
        Constants.MatchParticipantConstants.IndividualPosition position = Constants.MatchParticipantConstants.IndividualPosition.UTILITY;
        MatchParticipant matchParticipant1 = new MatchParticipantBuilder()
                .withIndividualPosition(position)
                .isWon(false)
                .build();
        MatchParticipant matchParticipant2 = new MatchParticipantBuilder()
                .withIndividualPosition(position)
                .isWon(true)
                .build();
        List<MatchParticipant> matchParticipantList = List.of(matchParticipant1, matchParticipant2);
        PreferedRoleDto preferedRoleDto = new PreferedRoleDtoBuilder()
                .withName(position)
                .withPickRatio(1.0)
                .withWinRatio(0.5)
                .withIconUrl()
                .build();
        List<PreferedRoleDto> expectedResult = List.of(preferedRoleDto);

        // When
        when(mockMatchParticipantRepository.findBySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(anyString()))
                .thenReturn(matchParticipantList);
        var toTest = new DbMatchService(mockSummonerRepository, mockItemRepository, mockTeamRepository, mockMatchRepository,
                mockMatchTeamRepository, mockMatchParticipantRepository, mockObjectiveRepository, mockPerkRepository,
                mockHttpSummonerService, mockChampionRepository, performanceStrategyFactory, matchParticipantUtils);
        var actualResult = toTest.getPreferredRole("Skilled Teaser");

        // Then
        assertEquals(expectedResult.size(), actualResult.size(), "Size is not the same");
        assertEquals(expectedResult, actualResult, "Results are not the same");
    }
}