package leagueoflegendsproject.Services.DbServices;

import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import leagueoflegendsproject.Controllers.ChampionController;
import leagueoflegendsproject.Controllers.ItemController;
import leagueoflegendsproject.Controllers.MatchController;
import leagueoflegendsproject.Controllers.PerkController;
import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;
import leagueoflegendsproject.DTOs.PreferedRoleDto;
import leagueoflegendsproject.Helpers.FileUtils;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Helpers.TestUtils.MatchParticipantBuilder;
import leagueoflegendsproject.Helpers.TestUtils.PlayersChampionStatsDtoBuilder;
import leagueoflegendsproject.Helpers.TestUtils.PreferedRoleDtoBuilder;
import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Repositories.*;
import leagueoflegendsproject.Services.HttpServices.HttpChampionService;
import leagueoflegendsproject.Services.HttpServices.HttpItemService;
import leagueoflegendsproject.Services.HttpServices.HttpPerkService;
import leagueoflegendsproject.Services.HttpServices.HttpSummonerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
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
    void setUp() throws IOException {
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

        List<Perk> perks = FileUtils.parseFileToObject(
                Objects.requireNonNull(getClass().getClassLoader().getResource("perks_response.csv")).getPath(),
                new TypeToken<List<Perk>>(){}
        );
        List<Item> items = FileUtils.parseFileToObject(
                Objects.requireNonNull(getClass().getClassLoader().getResource("items_response.csv")).getPath(),
                new TypeToken<List<Item>>(){}
        );
        List<Champion> champions = FileUtils.parseFileToObject(
                Objects.requireNonNull(getClass().getClassLoader().getResource("champions_response.csv")).getPath(),
                new TypeToken<List<Champion>>(){}
        );
        perkRepository.saveAll(perks);
        itemRepository.saveAll(items);
        championRepository.saveAll(champions);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMatchToDb() throws IOException {
        //given
        String matchResponsePath = Objects.requireNonNull(getClass().getClassLoader().getResource("matchResponse.json")).getPath();
        leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match =
                FileUtils.parseFileToObject(matchResponsePath, Match.class);

        // when
        var toTest = new DbMatchService(summonerRepository, itemRepository, teamRepository, matchRepository, banRepository, teamObjectiveRepository,
                matchTeamRepository, participantItemsRepository, matchParticipantRepository, objectiveRepository, perkRepository, matchParticipantPerkRepository,
                httpSummonerService, championRepository);
        toTest.AddMatchToDb(match);

        // then
        match.getInfo().getParticipants()
                .forEach(participant -> {
                    var actual = matchParticipantRepository.findById(new MatchParticipantKey(participant.getSummonerId(), match.getMetadata().getMatchId()));
                    assertTrue(actual.isPresent(), "There is not such a match participant in DB, summoner ID: " + participant.getSummonerId() + ", match ID: " + match.getMetadata().getMatchId());
                });
    }

    @Test
    void getMatchesByNickname() {
    }

    @Test
    void getChampionStatsByNickname_shouldReturnSetOfPlayersChampionStatsDto() {
        // Given
        String position = Constants.MatchParticipantConstants.IndividualPosition.UTILITY.toString();
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
        String position = Constants.MatchParticipantConstants.IndividualPosition.UTILITY.toString();
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