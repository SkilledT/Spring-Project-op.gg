package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.SummonersLeagueDto;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import leagueoflegendsproject.Services.DbServices.DbChampionService;
import leagueoflegendsproject.Services.DbServices.DbSummonerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
class HttpSummonerServiceTest {

    private RiotHttpClient mockRiotHttpClient;
    @Autowired
    private DbSummonerService dbSummonerService;
    @Autowired
    private HttpChampionService championService;
    @Autowired
    private DbChampionService dbChampionService;
    @Autowired
    private HttpItemService itemService;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        mockRiotHttpClient = mock(RiotHttpClient.class);
        var champions = championService.getChampionList();
        champions.forEach(e -> dbChampionService.saveChampion(e));
        itemService.refreshItems();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getSummonerByName_ShouldThrowException_WhenResponseFromApiIsNotASummonerObject() throws IOException, InterruptedException {
        String nickname = "someName";
        HttpResponseWrapper<Summoner> riotResponse =
                new HttpResponseWrapper<>(false, null, "Unable to retrieve object");
        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerByNicknameUrl(nickname);

        when(mockRiotHttpClient.get(url, Summoner.class))
                .thenReturn(riotResponse);
        var toTest = new HttpSummonerService(mockRiotHttpClient, dbSummonerService);
        var result = catchThrowable(() -> toTest.getSummonerByNameHTTP(nickname));

        assertThat(result)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unable to retrieve object");
    }

    @Test
    void getSummonerLeagueByNickname_ShouldThrowIllegalArgumentException_WhenResponseIsNotSuccess() throws IOException, InterruptedException {
        // given
        String nickname = "nickname";
        Summoner summoner = new Summoner("1", 123, 123, "nickname", "1000", "10", 100);
        HttpResponseWrapper<SummonerLeagueResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(false, null, "Unable to retrieve data from API");
        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerLeagueByNicknameUrl(summoner.getId());

        // when
        when(mockRiotHttpClient.get(url, SummonerLeagueResponseItem[].class))
                .thenReturn(responseWrapper);
        var toTest = spy(new HttpSummonerService(mockRiotHttpClient, dbSummonerService));
        doReturn(summoner).when(toTest).getSummonerByNameHTTP(nickname);

        // then
        var result = catchThrowable(() -> toTest.getSummonerLeagueByNicknameHTTP(nickname));
        assertThat(result)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unable to retrieve object");
    }

    @Test
    void getSummonerLeagueByNickname_ShouldThrowIllegalArgumentException_WhenResponseIsSuccessAndResponseIsNull() throws IOException, InterruptedException {
        // given
        String nickname = "nickname";
        Summoner summoner = new Summoner("1", 123, 123, nickname, "1000", "10", 100);
        HttpResponseWrapper<SummonerLeagueResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(true, null, "Unable to retrieve data from API");

        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerLeagueByNicknameUrl(summoner.getId());

        // when
        when(mockRiotHttpClient.get(url, SummonerLeagueResponseItem[].class))
                .thenReturn(responseWrapper);
        var toTest = spy(new HttpSummonerService(mockRiotHttpClient, dbSummonerService));
        doReturn(summoner).when(toTest).getSummonerByNameHTTP(nickname);
        var result = catchThrowable(() -> toTest.getSummonerLeagueByNicknameHTTP(nickname));

        // then
        assertThat(result)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unable to retrieve object");
    }

    @Test
    void getSummonerLeagueByNickname_ShouldReturnSummonerDTO_WhenResponseIsSuccessAndResponseIsNotNull() throws IOException, InterruptedException {
        // given
        String nickname = "nickname";
        Summoner summoner = new Summoner("1", 123, 123, nickname, "1000", "10", 100);

        SummonerLeagueResponseItem summonerLeagueResponseItem =
                new SummonerLeagueResponseItem(summoner.getName(), Constants.MatchParticipantConstants.RankedTier.GOLD.toString(), Constants.MatchParticipantConstants.RankedType.RANKED_SOLO_5x5.toString(), Constants.MatchParticipantConstants.RankedRank.II.toString());
        SummonerLeagueResponseItem summonerLeagueResponseItem2 =
                new SummonerLeagueResponseItem(summoner.getName(), Constants.MatchParticipantConstants.RankedTier.PLATINUM.toString(), Constants.MatchParticipantConstants.RankedType.RANKED_SOLO_3x3.toString(), Constants.MatchParticipantConstants.RankedRank.II.toString());
        SummonerLeagueResponseItem[] riotResponse = {summonerLeagueResponseItem, summonerLeagueResponseItem2};
        SummonersLeagueDto shouldReturn = new SummonersLeagueDto(riotResponse);

        HttpResponseWrapper<SummonerLeagueResponseItem[]> responseWrapper =
                new HttpResponseWrapper<>(true, riotResponse, "200 success");

        String url = RiotLinksProvider.SummonerLinksProvider.getSummonerLeagueByNicknameUrl(summoner.getId());

        // when
        when(mockRiotHttpClient.get(url, SummonerLeagueResponseItem[].class))
                .thenReturn(responseWrapper);
        var toTest = spy(new HttpSummonerService(mockRiotHttpClient, dbSummonerService));
        doReturn(summoner).when(toTest).getSummonerByNameHTTP(nickname);
        var serviceResponse = toTest.getSummonerLeagueByNicknameHTTP(nickname);

        // then
        assertEquals(shouldReturn, serviceResponse);
    }

    @Test
    void getSummonerChallengersNicknames() {
    }
}