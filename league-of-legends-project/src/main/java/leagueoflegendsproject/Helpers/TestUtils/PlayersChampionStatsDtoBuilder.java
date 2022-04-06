package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.DTOs.PlayersChampionStatsDto;

public class PlayersChampionStatsDtoBuilder {
    private PlayersChampionStatsDto playersChampionStatsDto;

    public PlayersChampionStatsDtoBuilder() {
        this.playersChampionStatsDto = new PlayersChampionStatsDto();
    }

    public PlayersChampionStatsDtoBuilder withWinRatio(double winRatio) {
        this.playersChampionStatsDto.setWinRatio(winRatio);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withChampName(String champName) {
        this.playersChampionStatsDto.setChampName(champName);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withCS(double cs) {
        this.playersChampionStatsDto.setCS(cs);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withAvgKills(double avgKills) {
        this.playersChampionStatsDto.setAvgKills(avgKills);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withAvgDeaths(double avgDeaths) {
        this.playersChampionStatsDto.setAvgDeaths(avgDeaths);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withAvgAssists(double avgAssists) {
        this.playersChampionStatsDto.setAvgAssists(avgAssists);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withPlayedMatches(int playedMatches) {
        this.playersChampionStatsDto.setPlayedMatches(playedMatches);
        return this;
    }

    public PlayersChampionStatsDtoBuilder withKDA(double KDA) {
        this.playersChampionStatsDto.setKDA(KDA);
        return this;
    }

    public PlayersChampionStatsDto build() {
        return this.playersChampionStatsDto;
    }

}
