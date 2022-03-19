package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Info;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import leagueoflegendsproject.Models.LoLApi.Summoner.SummonerName.Summoner;
import leagueoflegendsproject.Services.DbServices.DbMatchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HttpMatchServiceTest {


    private RiotHttpClient mockRiotHttpClient;
    private HttpSummonerService mockSummonerService;
    private DbMatchService mockDbMatchService;
    @Autowired
    private DbMatchService dbMatchService;

    @BeforeEach
    void setUp() {
        this.mockRiotHttpClient = mock(RiotHttpClient.class);
        this.mockSummonerService = mock(HttpSummonerService.class);
        this.dbMatchService = mock(DbMatchService.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMatchCollectionByNickname_ShouldThrowIllegalStateException_WhenIsNotSuccess() throws IOException, InterruptedException {
        String nickname = "Skilled";
        String puuid = "SkilledTeaserPuuid";
        int numberOfMatches = 20;
        String url = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=" + numberOfMatches;
        Summoner summoner = new Summoner();
        summoner.setPuuid(puuid);
        HttpResponseWrapper<String[]> responseWrapper =
                new HttpResponseWrapper<>(false, null, "Wrong request");

        when(mockSummonerService.getSummonerByNameHTTP(nickname)).thenReturn(summoner);
        when(mockRiotHttpClient.get(url, String[].class)).thenReturn(responseWrapper);

        var toTest = new HttpMatchService(mockRiotHttpClient, mockSummonerService, dbMatchService);

        var exception = catchThrowable(() -> toTest.getMatchCollectionByNickname(nickname, numberOfMatches));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Riot's API cannot be retried");
    }

    @Test
    void getMatchCollectionByNickname_ShouldReturnListOfMatches_WhenIsSuccess() throws IOException, InterruptedException {
        String nickname = "Skilled";
        String puuid = "SkilledTeaserPuuid";
        int numberOfMatches = 20;
        String url = RiotLinksProvider.MatchLinksProvider.getMatchCollectionUrl(puuid, numberOfMatches);

        Summoner summoner = new Summoner();
        summoner.setPuuid(puuid);

        Match fakeMatch = new Match();
        Info fakeInfo = new Info();
        fakeInfo.setGameName("game1");
        fakeMatch.setInfo(fakeInfo);

        Match fakeMatch2 = new Match();
        Info fakeInfo2 = new Info();
        fakeInfo2.setGameName("game2");
        fakeMatch2.setInfo(fakeInfo2);

        String[] responses = {"id1", "id2"};
        HttpResponseWrapper<String[]> responseWrapper =
                new HttpResponseWrapper<>(true, responses, "OK");

        when(mockSummonerService.getSummonerByNameHTTP(nickname)).thenReturn(summoner);
        when(mockRiotHttpClient.get(url, String[].class)).thenReturn(responseWrapper);

        HttpMatchService matchService = new HttpMatchService(mockRiotHttpClient, mockSummonerService, dbMatchService);
        var toTest = Mockito.spy(matchService);
        Mockito.doReturn(fakeMatch).when(toTest).getMatchById(responses[0]);
        Mockito.doReturn(fakeMatch2).when(toTest).getMatchById(responses[1]);

        assertThat(toTest.getMatchCollectionByNickname(nickname, numberOfMatches))
                .contains(fakeMatch);
        assertThat(toTest.getMatchCollectionByNickname(nickname, numberOfMatches))
                .contains(fakeMatch2);
        assertThat(toTest.getMatchCollectionByNickname(nickname, numberOfMatches))
                .contains(fakeMatch, fakeMatch2);
    }

    @Test
    void getMatchById_ShouldReturnMatchObject_WhenThereIsSuccess() throws IOException, InterruptedException {
        String fakeId = "idNo1";
        String fakeId2 = "idNo2";
        Match fakeMatch = new Match();
        Info fakeInfo = new Info();
        fakeInfo.setGameName("game1");
        fakeMatch.setInfo(fakeInfo);

        Match fakeMatch2 = new Match();
        Info fakeInfo2 = new Info();
        fakeInfo2.setGameName("game2");
        fakeMatch.setInfo(fakeInfo2);

        HttpResponseWrapper<Match> fakeHttpResponse = new HttpResponseWrapper<>(true, fakeMatch, "Wrong patch");
        HttpResponseWrapper<Match> fakeHttpResponse2 = new HttpResponseWrapper<>(true, fakeMatch2, "Wrong patch");
        String url = "https://europe.api.riotgames.com/lol/match/v5/matches/" + fakeId;
        String url2 = "https://europe.api.riotgames.com/lol/match/v5/matches/" + fakeId2;

        when(mockRiotHttpClient.get(url, Match.class)).thenReturn(fakeHttpResponse);
        when(mockRiotHttpClient.get(url2, Match.class)).thenReturn(fakeHttpResponse2);

        var toTest = new HttpMatchService(mockRiotHttpClient, mockSummonerService, dbMatchService);
        var result = toTest.getMatchById(fakeId);
        var result2 = toTest.getMatchById(fakeId2);

        assertThat(result).isInstanceOf(Match.class);
        assertEquals(result, fakeMatch);
        assertNotEquals(result, fakeMatch2);

        assertThat(result2).isInstanceOf(Match.class);
        assertNotEquals(result, result2);
        assertEquals(fakeMatch2, result2);
    }
    /*
    @Test
    void getChampionStatsByNickname() throws IOException, InterruptedException {
        String nickname = "SkilledT";
        String nickname2 = "noname";
        String puuid = "SkilledTPuuid";
        String puuid2 = "nonamePuuid";

        List<Match> fakeListOfMatches;

        ParticipantsItem fakeParticipant1 = new ParticipantsItem();
        fakeParticipant1.setAssists(3);
        fakeParticipant1.setKills(3);
        fakeParticipant1.setDeaths(3);
        fakeParticipant1.setChampionName("Shen");
        fakeParticipant1.setWin(true);
        fakeParticipant1.setPuuid(puuid);

        ParticipantsItem fakeParticipant2 = new ParticipantsItem();
        fakeParticipant2.setAssists(4);
        fakeParticipant2.setKills(4);
        fakeParticipant2.setDeaths(4);
        fakeParticipant2.setChampionName("Fizz");
        fakeParticipant2.setWin(true);
        fakeParticipant2.setPuuid(puuid);

        ParticipantsItem fakeParticipant3 = new ParticipantsItem();
        fakeParticipant3.setAssists(5);
        fakeParticipant3.setKills(5);
        fakeParticipant3.setDeaths(5);
        fakeParticipant3.setChampionName("Shen");
        fakeParticipant3.setWin(false);
        fakeParticipant3.setPuuid(puuid);

        ParticipantsItem fakeParticipant4 = new ParticipantsItem();
        fakeParticipant4.setAssists(4);
        fakeParticipant4.setKills(4);
        fakeParticipant4.setDeaths(4);
        fakeParticipant4.setChampionName("Fizz");
        fakeParticipant4.setWin(true);
        fakeParticipant4.setPuuid(puuid2);

        Match fakeMatch1 = new Match();
        Info fakeInfo1 = new Info();

        Match fakeMatch2 = new Match();
        Info fakeInfo2 = new Info();

        Match fakeMatch3 = new Match();
        Info fakeInfo3 = new Info();

        fakeInfo1.setParticipants(List.of(fakeParticipant1));
        fakeInfo1.setParticipants(List.of(fakeParticipant4));
        fakeInfo2.setParticipants(List.of(fakeParticipant2));
        fakeInfo3.setParticipants(List.of(fakeParticipant3));

        fakeMatch1.setInfo(fakeInfo1);
        fakeMatch2.setInfo(fakeInfo2);
        fakeMatch3.setInfo(fakeInfo3);

        fakeListOfMatches = List.of(fakeMatch1, fakeMatch2, fakeMatch3);

        Summoner summoner1 = new Summoner();
        Summoner summoner2 = new Summoner();
        summoner1.setPuuid(puuid);
        summoner2.setPuuid(puuid2);

        PlayersChampionStatsDto playersChampionStatsDto1 = new PlayersChampionStatsDto();
        playersChampionStatsDto1.setAvgAssists(4.0d);
        playersChampionStatsDto1.setAvgDeaths(4.0d);
        playersChampionStatsDto1.setAvgKills(4.0d);
        playersChampionStatsDto1.setChampName("Shen");
        playersChampionStatsDto1.setWinRatio(0.5d);

        PlayersChampionStatsDto playersChampionStatsDto2 = new PlayersChampionStatsDto();
        playersChampionStatsDto2.setAvgAssists(4.0d);
        playersChampionStatsDto2.setAvgDeaths(4.0d);
        playersChampionStatsDto2.setAvgKills(4.0d);
        playersChampionStatsDto2.setChampName("Fizz");
        playersChampionStatsDto2.setWinRatio(1.0d);

        when(mockSummonerService.getSummonerByNameHTTP(nickname)).thenReturn(summoner1);
        when(mockSummonerService.getSummonerByNameHTTP(nickname2)).thenReturn(summoner2);

        HttpMatchService httpMatchService = new HttpMatchService(mockRiotHttpClient, mockSummonerService);
        var toTest = Mockito.spy(httpMatchService);
        Mockito.doReturn(fakeListOfMatches).when(toTest).getMatchCollectionByNickname(nickname);
        var result = toTest.getChampionStatsByNickname(nickname);
        System.out.println(result);

        assertThat(result).contains(playersChampionStatsDto1);
        assertThat(result).contains(playersChampionStatsDto2);
    }*/

}