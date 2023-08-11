package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChampionServiceTest {
    private ChampionService championService;

    @Mock
    private RiotHttpClient httpClientMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        championService = new ChampionService(httpClientMock);
    }

    @Test
    public void testGetChampions_WhenHttpClientReturnsValidResponse_ShouldReturnChampionItems() throws IOException, InterruptedException {
        // Mocking the HttpClient and its response
        String responseJson = new String(Files.readAllBytes(Paths.get("src/test/resources/champions_mock.json")));
        HttpResponseWrapper<String> validResponse = new HttpResponseWrapper<>(true, responseJson, null);
        when(httpClientMock.get(eq(RiotLinksProvider.RIOT_CHAMPION_URL), eq(String.class))).thenReturn(validResponse);

        // Calling the method and asserting the result
        HttpResponseWrapper<List<ChampionItem>> resultWrapper = championService.getChampions();
        assertNotNull(resultWrapper);
        assertTrue(resultWrapper.isSuccess());

        List<ChampionItem> resultList = resultWrapper.getResponse();
        resultList.sort(Comparator.comparing(ChampionItem::getName));
        assertEquals(163, resultList.size());

        ChampionItem champion1 = resultWrapper.getResponse().get(0);
        assertEquals("Aatrox", champion1.getName());
        assertEquals("the Darkin Blade", champion1.getTitle());

        ChampionItem champion2 = resultWrapper.getResponse().get(1);
        assertEquals("Ahri", champion2.getName());
        assertEquals("the Nine-Tailed Fox", champion2.getTitle());

        // Verifying that the HttpClient was called with the correct URL and response type
        verify(httpClientMock, times(1)).get(eq(RiotLinksProvider.RIOT_CHAMPION_URL), eq(String.class));
    }
}
