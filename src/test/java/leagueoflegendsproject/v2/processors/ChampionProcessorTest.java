package leagueoflegendsproject.v2.processors;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services.IntegrationChampionService;
import leagueoflegendsproject.v2.Models.PatchVersion;
import leagueoflegendsproject.v2.Repositories.ChampionSnapshotRepository;
import leagueoflegendsproject.v2.Services.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ChampionProcessorTest {

    @Mock
    private PatchVersionService mockedPatchVersionService;

    @Autowired
    private ChampionSnapshotRepository championSnapshotRepository;
    @Autowired
    private ChampionSnapshotService championService;
    @Autowired
    private ChampionInfoService championInfoService;
    @Autowired
    private ChampionStatsService championStatsService;
    @Autowired
    private ChampionImageService championImageService;
    @Autowired
    private IntegrationChampionService integrationChampionService;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    void beforeEach() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldCreateChampionSnapshotsWhenResponseIs2xx() throws IOException, URISyntaxException {
        var versionName = "13.13";
        var version = new PatchVersion()
                .setId(1L)
                .setVersion(versionName);

        File resourceFile = new File(getClass().getClassLoader()
                .getResource("lolapiresponses/champion/champions_mock.json")
                .getFile());
        String responseBody = Files.readString(resourceFile.toPath());

        mockRestServiceServer.expect(ExpectedCount.manyTimes(),
                        requestTo(new URI("http://ddragon.leagueoflegends.com/cdn/13.13.1/data/en_US/champion.json")))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(responseBody)
                );

        when(mockedPatchVersionService.findAll()).thenReturn(List.of(version));
        var processor = new ChampionProcessor(championService, championInfoService, championStatsService,
                championImageService, integrationChampionService, mockedPatchVersionService);
        processor.fetchAndSaveChampions();

        // then
        var snaps = championSnapshotRepository.findAll();
        mockRestServiceServer.verify();
        Assertions.assertEquals(163L, snaps.size());
    }

    @ParameterizedTest
    @CsvSource({
            "400",
            "401",
            "404",
            "500"
    })
    void shouldNotCreateChampionSnapshotsWhenResponseIs4xxAnd5xx(Integer statusCode) throws URISyntaxException {
        var versionName = "13.13";
        var version = new PatchVersion()
                .setId(1L)
                .setVersion(versionName);

        mockRestServiceServer.expect(ExpectedCount.manyTimes(),
                        requestTo(new URI("http://ddragon.leagueoflegends.com/cdn/13.13.1/data/en_US/champion.json")))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.valueOf(statusCode))
                        .contentType(MediaType.APPLICATION_JSON)
                );

        when(mockedPatchVersionService.findAll()).thenReturn(List.of(version));
        var processor = new ChampionProcessor(championService, championInfoService, championStatsService,
                championImageService, integrationChampionService, mockedPatchVersionService);
        processor.fetchAndSaveChampions();

        // then
        var snaps = championSnapshotRepository.findAll();
        Assertions.assertEquals(0L, snaps.size());
    }
}
