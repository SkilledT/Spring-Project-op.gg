package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.ItemDto;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Helpers.TestUtils.HttpItemBuilder;
import leagueoflegendsproject.Models.LoLApi.Items.Gold;
import leagueoflegendsproject.Models.LoLApi.Items.Image;
import leagueoflegendsproject.Models.LoLApi.Items.Item;
import leagueoflegendsproject.Repositories.ItemRepository;
import leagueoflegendsproject.Services.DbServices.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class HttpItemServiceTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    private RiotHttpClient mockRiotHttpClient;

    @BeforeEach
    void setUp() {
        mockRiotHttpClient = mock(RiotHttpClient.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void refreshItems_ShouldReturnsEmptyList_WhenIsNotSuccess() throws IOException, InterruptedException {
        HttpResponseWrapper<List<Item>> httpResponseWrapper =
                new HttpResponseWrapper<>(false, null, "API is not available now");

        when(mockRiotHttpClient.getItems())
                .thenReturn(httpResponseWrapper);
        var toTest = new HttpItemService(itemService, mockRiotHttpClient);
        var responseCollection = toTest.refreshItems();

        assertEquals(Collections.emptyList(), responseCollection);
    }

    @Test
    void refreshItems_ShouldReturnsItemList_WhenIsSuccess() throws IOException, InterruptedException {
        // Related Objects
        Gold gold = new Gold(100, true, 200, 300);
        Image image = new Image();
        image.setFull("full.png");
        // HttpItems
        Item item1 = new HttpItemBuilder()
                .withGold(gold)
                .withImage(image)
                .withDescription("desc1")
                .withName("Rabadon's Deathcap")
                .withPlainText("setPlaintext1")
                .withId(1)
                .withColloq("setColloq1")
                .build();

        Item item2 = new HttpItemBuilder()
                .withGold(gold)
                .withImage(image)
                .withDescription("desc2")
                .withName("Tear of Goddess")
                .withPlainText("setPlaintext2")
                .withId(2)
                .withColloq("setColloq2")
                .build();
        List<Item> httpItems = List.of(item1, item2);

        //DTO Items
        ItemDto itemDto1 = new ItemDto();
        itemDto1.setName("Rabadon's Deathcap");
        itemDto1.setDescription("desc1");
        itemDto1.setBaseCost(gold.getBase());
        itemDto1.setSell(gold.getSell());
        itemDto1.setTotalCost(gold.getTotal());
        itemDto1.setIconUrl(RiotLinksProvider.ItemLinkProvider.getIconUrl("full"));
        itemDto1.setPlainText("setPlaintext1");
        ItemDto itemDto2 = new ItemDto();
        itemDto2.setName("Tear of Goddess");
        itemDto2.setDescription("desc2");
        itemDto2.setBaseCost(gold.getBase());
        itemDto2.setSell(gold.getSell());
        itemDto2.setTotalCost(gold.getTotal());
        itemDto2.setIconUrl(RiotLinksProvider.ItemLinkProvider.getIconUrl("full"));
        itemDto2.setPlainText("setPlaintext2");
        List<ItemDto> dtoItems = List.of(itemDto1, itemDto2);

        HttpResponseWrapper<List<Item>> httpResponseWrapper =
                new HttpResponseWrapper<>(true, httpItems, "200 OK");

        when(mockRiotHttpClient.getItems())
                .thenReturn(httpResponseWrapper);
        var toTest = new HttpItemService(itemService, mockRiotHttpClient);
        var responseCollection = toTest.refreshItems();

        assertEquals(dtoItems, responseCollection);
        assertEquals(2, itemRepository.findAll().size());
    }
}