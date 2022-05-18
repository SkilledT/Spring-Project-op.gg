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
class HttpPerkServiceTest {

    private RiotHttpClient mockRiotHttpClient;
    private DbPerkService mockDbPerkService;
    @Autowired
    private PerkRepository perkRepository;
    @Autowired
    private DbPerkService dbPerkService;

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
        HttpResponseWrapper<ResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(false, null, "Wrong request");

        when(mockRiotHttpClient.get(RiotLinksProvider.RIOT_CHAMPION_PERKS_URL, ResponseItem[].class))
                .thenReturn(responseWrapper);

        var toTest = new HttpPerkService(mockRiotHttpClient);
        var exception = catchThrowable(toTest::getPerks);

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unable to retrieve data from Riot API");
    }

    @Test
    void getPerks_ShouldReturnPerksList_WhenResponseIsNotNull() throws IOException, InterruptedException {
        RunesItem runesItem1 = new RunesItem();
        runesItem1.setIcon("icon1");
        runesItem1.setName("name1");
        runesItem1.setKey("key1");
        runesItem1.setShortDesc("shortdesc1");
        runesItem1.setLongDesc("longdesc1");
        runesItem1.setId(0);

        RunesItem runesItem2 = new RunesItem();
        runesItem2.setIcon("icon2");
        runesItem2.setName("name2");
        runesItem2.setKey("key2");
        runesItem2.setShortDesc("shortdesc2");
        runesItem2.setLongDesc("longdesc2");
        runesItem2.setId(2);

        RunesItem runesItem3 = new RunesItem();
        runesItem3.setIcon("icon3");
        runesItem3.setName("name3");
        runesItem3.setKey("key3");
        runesItem3.setShortDesc("shortdesc3");
        runesItem3.setLongDesc("longdesc3");
        runesItem3.setId(3);

        SlotsItem slotsItem1 = new SlotsItem();
        List<SlotsItem> slotsItem1List = new ArrayList<>();
        List<RunesItem> runesItemList1 = new ArrayList<>();
        runesItemList1.add(runesItem1);
        runesItemList1.add(runesItem2);
        slotsItem1.setRunes(runesItemList1);
        slotsItem1List.add(slotsItem1);

        SlotsItem slotsItem2 = new SlotsItem();
        List<SlotsItem> slotsItem2List = new ArrayList<>();
        List<RunesItem> runesItemList2 = new ArrayList<>();
        runesItemList2.add(runesItem3);
        slotsItem2List.add(slotsItem2);
        slotsItem2.setRunes(runesItemList2);

        ResponseItem responseItem1 = new ResponseItem();
        responseItem1.setSlots(slotsItem1List);

        ResponseItem responseItem2 = new ResponseItem();
        responseItem2.setSlots(slotsItem2List);

        ResponseItem[] responseItemsResult = {responseItem1, responseItem2};

        HttpResponseWrapper<ResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(true, responseItemsResult, "All right");

        Perk perk1 = new Perk();
        perk1.setId(0);
        perk1.setIcon("icon1");
        perk1.setLongDesc("longdesc1");
        perk1.setShortDesc("shortdesc1");
        perk1.setName("name1");
        perk1.setSlotNumber(0);
        perk1.setTreeNumber(0);

        Perk perk2 = new Perk();
        perk2.setId(2);
        perk2.setIcon("icon2");
        perk2.setLongDesc("longdesc2");
        perk2.setShortDesc("shortdesc2");
        perk2.setName("name2");
        perk2.setSlotNumber(0);
        perk2.setTreeNumber(0);

        Perk perk3 = new Perk();
        perk3.setId(3);
        perk3.setIcon("icon3");
        perk3.setLongDesc("longdesc3");
        perk3.setShortDesc("shortdesc3");
        perk3.setName("name3");
        perk3.setSlotNumber(0);
        perk3.setTreeNumber(1);

        when(mockRiotHttpClient.get(RiotLinksProvider.RIOT_CHAMPION_PERKS_URL, ResponseItem[].class))
                .thenReturn(responseWrapper);

        var toTest = new HttpPerkService(mockRiotHttpClient);
        var resultList = toTest.getPerks();

        assertEquals(3, resultList.size());
        assertEquals(resultList.get(0), perk1);
        assertEquals(resultList.get(1), perk2);
        assertEquals(resultList.get(2), perk3);
        List<Perk> dbRecords = perkRepository.findAll();
        assertEquals(3, dbRecords.size());
        assertTrue(dbRecords.stream().anyMatch(e -> e.equals(perk1)));
        assertTrue(dbRecords.stream().anyMatch(e -> e.equals(perk2)));
        assertTrue(dbRecords.stream().anyMatch(e -> e.equals(perk3)));
    }
}