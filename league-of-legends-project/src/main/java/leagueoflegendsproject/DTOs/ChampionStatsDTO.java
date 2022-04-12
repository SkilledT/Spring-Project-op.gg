package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.ChampionStats;

import java.util.Objects;

public class ChampionStatsDTO {
    private Double winRatio;
    private String championName;
    private Integer numberOfMatches;
    private String iconUrl;

    public ChampionStatsDTO(ChampionStats championStats) {
        this.winRatio = championStats.getWinRatio();
        this.championName = championStats.getEnemyChampion();
        this.numberOfMatches = championStats.getGames();
        this.iconUrl = RiotLinksProvider.ChampionLinkProvider.getIconImg(championStats.getEnemyChampion());
    }

    public ChampionStatsDTO() {
    }

    public Double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(Double winRatio) {
        this.winRatio = winRatio;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public Integer getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(Integer numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionStatsDTO that = (ChampionStatsDTO) o;
        return Objects.equals(winRatio, that.winRatio) && Objects.equals(championName, that.championName) && Objects.equals(numberOfMatches, that.numberOfMatches) && Objects.equals(iconUrl, that.iconUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winRatio, championName, numberOfMatches, iconUrl);
    }
}
