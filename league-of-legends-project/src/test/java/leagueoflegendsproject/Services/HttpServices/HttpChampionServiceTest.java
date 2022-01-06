package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class HttpChampionServiceTest {

    private RiotHttpClient mockRiotHttpClient;

    @BeforeEach
    void setUp() {
        mockRiotHttpClient = mock(RiotHttpClient.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getChampionList_ShouldReturnEmptyList_WhenIsNotSuccess() {
        HttpResponseWrapper<List<ChampionItem>> httpResponseWrapper =
                new HttpResponseWrapper<>(false, null, "API is not available");
        when(mockRiotHttpClient.getChampions())
                .thenReturn(httpResponseWrapper);

        var toTest = new HttpChampionService(mockRiotHttpClient);
        var methodCollection = toTest.getChampionList();

        assertEquals(methodCollection, Collections.emptyList());
    }

    @Test
    void getChampionList_ShouldReturnReceivedCollection_WhenIsSuccess() {
        ChampionItem championItem1 = new ChampionItem();
        championItem1.setId("Akali");
        championItem1.setName("Akali");
        ChampionItem championItem2 = new ChampionItem();
        championItem2.setId("Ahri");
        championItem2.setName("Ahri");
        List<ChampionItem> championItemList = List.of(championItem1,  championItem2);
        HttpResponseWrapper<List<ChampionItem>> httpResponseWrapper =
                new HttpResponseWrapper<>(true, championItemList, "API is not available");
        when(mockRiotHttpClient.getChampions())
                .thenReturn(httpResponseWrapper);

        var toTest = new HttpChampionService(mockRiotHttpClient);
        var methodCollection = toTest.getChampionList();

        assertEquals(methodCollection, championItemList);
        assertEquals(methodCollection.size(), championItemList.size());
    }
}