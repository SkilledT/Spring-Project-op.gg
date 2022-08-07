package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Info;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import leagueoflegendsproject.Services.DbServices.DbMatchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HttpMatchServiceTest {


    private RiotHttpClient mockRiotHttpClient;
    private HttpSummonerService mockSummonerService;
    private DbMatchService mockDbMatchService;
    private HttpSummonerService mockHttpSummonerService;
    @Autowired
    private DbMatchService dbMatchService;

    @BeforeEach
    void setUp() {
        this.mockRiotHttpClient = mock(RiotHttpClient.class);
        this.mockSummonerService = mock(HttpSummonerService.class);
        this.mockDbMatchService = mock(DbMatchService.class);
        this.mockHttpSummonerService = mock(HttpSummonerService.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMatchCollectionByNickname_ShouldThrowIllegalStateException_WhenIsNotSuccess() throws IOException, InterruptedException {
        // given
        String nickname = "Skilled";
        String puuid = "SkilledTeaserPuuid";
        int numberOfMatches = 20;
        String url = RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches);
        Summoner summoner = new Summoner();
        summoner.setPuuid(puuid);
        HttpResponseWrapper<String[]> responseWrapper =
                new HttpResponseWrapper<>(false, null, "Wrong request");

        // when
        when(mockSummonerService.getSummonerByNameHTTP(nickname)).thenReturn(summoner);
        when(mockRiotHttpClient.get(url, String[].class)).thenReturn(responseWrapper);

        var toTest = new HttpMatchService(mockRiotHttpClient, mockSummonerService, dbMatchService, mockHttpSummonerService);
        var exception = catchThrowable(() -> toTest.getMatchCollectionByNickname(nickname, numberOfMatches));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Riot's API cannot be retried");
    }

    @Test
    void getMatchCollectionByNickname_ShouldReturnListOfMatches_WhenIsSuccess() throws IOException, InterruptedException {
        // given
        String nickname = "Skilled";
        String puuid = "SkilledTeaserPuuid";
        int numberOfMatches = 20;
        String url = RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches);

        Summoner summoner = new Summoner();
        summoner.setPuuid(puuid);

        Info fakeInfo = new Info("game1");
        Match fakeMatch = new Match(fakeInfo);

        Info fakeInfo2 = new Info("game2");
        Match fakeMatch2 = new Match(fakeInfo2);

        String[] responses = {"id1", "id2"};
        HttpResponseWrapper<String[]> responseWrapper =
                new HttpResponseWrapper<>(true, responses, "OK");

        // when
        when(mockSummonerService.getSummonerByNameHTTP(nickname)).thenReturn(summoner);
        when(mockRiotHttpClient.get(url, String[].class)).thenReturn(responseWrapper);

        HttpMatchService matchService = new HttpMatchService(mockRiotHttpClient, mockSummonerService, mockDbMatchService, mockHttpSummonerService);
        var toTest = Mockito.spy(matchService);
        Mockito.doReturn(fakeMatch).when(toTest).getMatchById(responses[0]);
        Mockito.doReturn(fakeMatch2).when(toTest).getMatchById(responses[1]);

        // then
        assertThat(toTest.getMatchCollectionByNickname(nickname, numberOfMatches))
                .contains(fakeMatch);
        assertThat(toTest.getMatchCollectionByNickname(nickname, numberOfMatches))
                .contains(fakeMatch2);
        assertThat(toTest.getMatchCollectionByNickname(nickname, numberOfMatches))
                .contains(fakeMatch, fakeMatch2);
    }

    @Test
    void getMatchById_ShouldReturnMatchObject_WhenThereIsSuccess() throws IOException, InterruptedException {
        // given
        String fakeId = "idNo1";
        String fakeId2 = "idNo2";
        Info fakeInfo = new Info("game1");
        Match fakeMatch = new Match(fakeInfo);

        Info fakeInfo2 = new Info("game2");
        Match fakeMatch2 = new Match(fakeInfo2);

        HttpResponseWrapper<Match> fakeHttpResponse = new HttpResponseWrapper<>(true, fakeMatch, "Wrong patch");
        HttpResponseWrapper<Match> fakeHttpResponse2 = new HttpResponseWrapper<>(true, fakeMatch2, "Wrong patch");
        String url = RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(fakeId);
        String url2 = RiotLinksProvider.MatchLinksProvider.getMatchDetailsUrl(fakeId2);

        // when
        when(mockRiotHttpClient.get(url, Match.class)).thenReturn(fakeHttpResponse);
        when(mockRiotHttpClient.get(url2, Match.class)).thenReturn(fakeHttpResponse2);

        var toTest = new HttpMatchService(mockRiotHttpClient, mockSummonerService, dbMatchService, mockHttpSummonerService);
        var result = toTest.getMatchById(fakeId);
        var result2 = toTest.getMatchById(fakeId2);

        // then
        assertThat(result).isInstanceOf(Match.class);
        assertEquals(result, fakeMatch);
        assertNotEquals(result, fakeMatch2);

        assertThat(result2).isInstanceOf(Match.class);
        assertNotEquals(result, result2);
        assertEquals(fakeMatch2, result2);
    }
}