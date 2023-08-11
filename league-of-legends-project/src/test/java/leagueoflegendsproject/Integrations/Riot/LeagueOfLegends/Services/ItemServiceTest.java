package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemService itemService;

    @Mock
    private RiotHttpClient httpClientMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(httpClientMock);
    }

    @Test
    public void testGetChampions_WhenHttpClientReturnsValidResponse_ShouldReturnChampionItems() throws IOException, InterruptedException {
        // Mocking the HttpClient and its response
        String responseJson = new String(Files.readAllBytes(Paths.get("src/test/resources/items_mock.json")));
        HttpResponseWrapper<String> validResponse = new HttpResponseWrapper<>(true, responseJson, null);
        when(httpClientMock.get(eq(RiotLinksProvider.ItemLinkProvider.RIOT_ITEMS_URL), eq(String.class))).thenReturn(validResponse);

        // Calling the method and asserting the result
        HttpResponseWrapper<List<Item>> resultWrapper = itemService.getItems();
        assertNotNull(resultWrapper);
        assertTrue(resultWrapper.isSuccess());

        List<Item> resultList = resultWrapper.getResponse();
        resultList.sort(Comparator.comparing(Item::getId));

        Item item1 = resultWrapper.getResponse().get(0);
        assertEquals(1001, item1.getId());
        assertEquals("Boots", item1.getName());

        Item item2 = resultWrapper.getResponse().get(1);
        assertEquals(1004, item2.getId());
        assertEquals("Faerie Charm", item2.getName());

        // Verifying that the HttpClient was called with the correct URL and response type
        verify(httpClientMock, times(1)).get(eq(RiotLinksProvider.ItemLinkProvider.RIOT_ITEMS_URL), eq(String.class));
    }
}