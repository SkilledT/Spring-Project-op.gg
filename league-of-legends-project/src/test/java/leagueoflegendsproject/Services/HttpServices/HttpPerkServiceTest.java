package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Models.LoLApi.Perks.ResponseItem;
import leagueoflegendsproject.Models.LoLApi.Perks.RunesItem;
import leagueoflegendsproject.Models.LoLApi.Perks.SlotsItem;
import leagueoflegendsproject.Repositories.PerkRepository;
import leagueoflegendsproject.Services.DbServices.DbPerkService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
class HttpPerkServiceTest {

    private RiotHttpClient mockRiotHttpClient;
    private DbPerkService mockDbPerkService;

    @BeforeEach
    void setUp() {
        this.mockDbPerkService = mock(DbPerkService.class);
        this.mockRiotHttpClient = mock(RiotHttpClient.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPerks_ShouldThrowException_WhenResponseIsNull() throws IOException, InterruptedException {
        // given
        HttpResponseWrapper<ResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(false, null, "Wrong request");

        // when
        when(mockRiotHttpClient.get(RiotLinksProvider.RIOT_CHAMPION_PERKS_URL, ResponseItem[].class))
                .thenReturn(responseWrapper);
        var toTest = new HttpPerkService(mockRiotHttpClient);
        var exception = catchThrowable(toTest::getPerks);

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unable to retrieve data from Riot API");
    }

    @Test
    void getPerks_ShouldReturnPerksList_WhenResponseIsNotNull() throws IOException, InterruptedException {
        // given
        RunesItem runesItem1 = new RunesItem("icon1", "name1", "shortdesc1", 0, "key1", "longdesc1");
        RunesItem runesItem2 = new RunesItem("icon2", "name2", "shortdesc2", 2, "key2", "longdesc2");
        RunesItem runesItem3 = new RunesItem("icon3", "name3", "shortdesc3", 3, "key3", "longdesc3");

        SlotsItem slotsItem1 = new SlotsItem();
        List<SlotsItem> slotsItem1List = List.of(slotsItem1);
        List<RunesItem> runesItemList1 = List.of(runesItem1, runesItem2);
        slotsItem1.setRunes(runesItemList1);

        SlotsItem slotsItem2 = new SlotsItem();
        List<SlotsItem> slotsItem2List = List.of(slotsItem2);
        List<RunesItem> runesItemList2 = List.of(runesItem3);
        slotsItem2.setRunes(runesItemList2);

        ResponseItem responseItem1 = new ResponseItem(slotsItem1List);
        ResponseItem responseItem2 = new ResponseItem(slotsItem2List);

        ResponseItem[] responseItemsResult = {responseItem1, responseItem2};

        HttpResponseWrapper<ResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(true, responseItemsResult, "All right");

        Perk perk1 = new Perk(0, "name1", "icon1", "shortdesc1", "longdesc1", 0, 0);
        Perk perk2 = new Perk(2, "name2", "icon2", "shortdesc2", "longdesc2", 0, 0);
        Perk perk3 = new Perk(3, "name3", "icon3", "shortdesc3", "longdesc3", 0, 1);

        // when
        when(mockRiotHttpClient.get(RiotLinksProvider.RIOT_CHAMPION_PERKS_URL, ResponseItem[].class))
                .thenReturn(responseWrapper);

        var toTest = new HttpPerkService(mockRiotHttpClient);
        var resultList = toTest.getPerks();

        // then
        assertEquals(3, resultList.size());
        assertEquals(resultList.get(0), perk1);
        assertEquals(resultList.get(1), perk2);
        assertEquals(resultList.get(2), perk3);
    }
}