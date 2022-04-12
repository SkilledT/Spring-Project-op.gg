package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;
import leagueoflegendsproject.DTOs.PreferedRoleDto;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Helpers.TestUtils.MatchParticipantBuilder;
import leagueoflegendsproject.Helpers.TestUtils.PlayersChampionStatsDtoBuilder;
import leagueoflegendsproject.Helpers.TestUtils.PreferedRoleDtoBuilder;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DbMatchServiceTest {

    private SummonerRepository mockSummonerRepository;
    private ItemRepository mockItemRepository;
    private TeamRepository mockTeamRepository;
    private MatchRepository mockMatchRepository;
    private ParticipantItemsRepository mockParticipantItemsRepository;
    private MatchParticipantRepository mockMatchParticipantRepository;
    private BanRepository mockBanRepository;
    private MatchTeamRepository mockMatchTeamRepository;
    private ChampionRepository mockChampionRepository;
    private TeamObjectiveRepository mockTeamObjectiveRepository;
    private ObjectiveRepository mockObjectiveRepository;
    private MatchParticipantPerkRepository mockMatchParticipantPerkRepository;
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


    @BeforeEach
    void setUp() {
        this.mockSummonerRepository = mock(SummonerRepository.class);
        this.mockItemRepository = mock(ItemRepository.class);
        this.mockTeamRepository = mock(TeamRepository.class);
        this.mockMatchRepository = mock(MatchRepository.class);
        this.mockParticipantItemsRepository = mock(ParticipantItemsRepository.class);
        this.mockMatchParticipantRepository = mock(MatchParticipantRepository.class);
        this.mockBanRepository = mock(BanRepository.class);
        this.mockMatchTeamRepository = mock(MatchTeamRepository.class);
        this.mockChampionRepository = mock(ChampionRepository.class);
        this.mockTeamObjectiveRepository = mock(TeamObjectiveRepository.class);
        this.mockObjectiveRepository = mock(ObjectiveRepository.class);
        this.mockMatchParticipantPerkRepository = mock(MatchParticipantPerkRepository.class);
        this.mockPerkRepository = mock(PerkRepository.class);
        this.mockHttpSummonerService = mock(HttpSummonerService.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMatchToDb() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("matchResponse.json").getFile());
        StringBuilder builder = new StringBuilder();
        Files.lines(Path.of(file.getPath())).forEach(e -> builder.append(e + '\n'));
        leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match = RiotHttpClient.parseResponseToClassObject(builder.toString(), Match.class);

        var toTest = new DbMatchService(summonerRepository, itemRepository, teamRepository, matchRepository, banRepository, teamObjectiveRepository,
                matchTeamRepository, participantItemsRepository, matchParticipantRepository, objectiveRepository, perkRepository, matchParticipantPerkRepository,
                httpSummonerService, championRepository);
        toTest.AddMatchToDb(match);
    }

    @Test
    void getMatchesByNickname() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("res.csv").getFile());
        StringBuilder builder = new StringBuilder();
        Files.lines(Path.of(file.getPath())).forEach(e -> builder.append(e + '\n'));
    }

    @Test
    void getChampionStatsByNickname_shouldReturnSetOfPlayersChampionStatsDto() {
        // Given
        String position = Constants.MatchParticipantConstnts.IndividualPosition.UTILITY.toString();
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
        when(mockMatchParticipantRepository.findTop10BySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(anyString()))
                .thenReturn(matchParticipantList);
        var toTest = new DbMatchService(mockSummonerRepository, mockItemRepository, mockTeamRepository, mockMatchRepository, mockBanRepository, mockTeamObjectiveRepository,
                mockMatchTeamRepository, mockParticipantItemsRepository, mockMatchParticipantRepository, mockObjectiveRepository, mockPerkRepository, mockMatchParticipantPerkRepository,
                mockHttpSummonerService, mockChampionRepository);
        var actualResult = toTest.getChampionStatsByNickname("Skilled Teaser");

        // Then
        assertEquals(expectedResult.size(), actualResult.size(), "size is not equals");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPreferredRole_shouldReturnDTOList() {
        // Given
        String position = Constants.MatchParticipantConstnts.IndividualPosition.UTILITY.toString();
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
        when(mockMatchParticipantRepository.findTop10BySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(anyString()))
                .thenReturn(matchParticipantList);
        var toTest = new DbMatchService(mockSummonerRepository, mockItemRepository, mockTeamRepository, mockMatchRepository, mockBanRepository, mockTeamObjectiveRepository,
                mockMatchTeamRepository, mockParticipantItemsRepository, mockMatchParticipantRepository, mockObjectiveRepository, mockPerkRepository, mockMatchParticipantPerkRepository,
                mockHttpSummonerService, mockChampionRepository);
        var actualResult = toTest.getPreferredRole("Skilled Teaser");

        // Then
        assertEquals(expectedResult.size(), actualResult.size(), "Size is not the same");
        assertEquals(expectedResult, actualResult, "Results are not the same");
    }
}