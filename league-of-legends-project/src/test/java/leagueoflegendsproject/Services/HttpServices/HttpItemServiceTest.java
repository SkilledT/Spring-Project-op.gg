package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.ItemDto;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Helpers.TestUtils.HttpItemBuilder;
import leagueoflegendsproject.Helpers.TestUtils.ItemDtoBuilder;
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
        ItemDto itemDto1 = new ItemDtoBuilder()
                .withName("Rabadon's Deathcap")
                .withLongDesc("desc1")
                .withBasedCost(gold.getBase())
                .withSellPrice(gold.getSell())
                .withTotalCost(gold.getTotal())
                .withIconUrl(RiotLinksProvider.ItemLinkProvider.getIconUrl("full"))
                .withPlainText("setPlaintext1")
                .build();
        ItemDto itemDto2 = new ItemDtoBuilder()
                .withName("Tear of Goddess")
                .withLongDesc("desc2")
                .withBasedCost(gold.getBase())
                .withSellPrice(gold.getSell())
                .withTotalCost(gold.getTotal())
                .withIconUrl(RiotLinksProvider.ItemLinkProvider.getIconUrl("full"))
                .withPlainText("setPlaintext2")
                .build();;
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