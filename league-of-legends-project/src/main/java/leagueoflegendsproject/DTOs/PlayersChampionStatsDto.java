package leagueoflegendsproject.DTOs;

import java.util.Objects;

public class PlayersChampionStatsDto {
    private double winRatio;
    private String champName;
    private double CS;
    private int playedMatches;
    private double avgKills;
    private double avgDeaths;
    private double avgAssists;
    private double KDA;
    private String champUrl;

    public PlayersChampionStatsDto(double winRatio,
                                   String champName,
                                   double CS,
                                   int playedMatches,
                                   double avgKills,
                                   double avgDeaths,
                                   double avgAssists) {
        this.winRatio = winRatio;
        this.champName = champName;
        this.CS = CS;
        this.playedMatches = playedMatches;
        this.avgKills = avgKills;
        this.avgDeaths = avgDeaths;
        this.avgAssists = avgAssists;
        this.champUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/"+champName+".png";
    }

    public PlayersChampionStatsDto(double winRatio, String champName, double CS, int playedMatches, double avgKills, double avgDeaths, double avgAssists, double KDA, String champUrl) {
        this.winRatio = winRatio;
        this.champName = champName;
        this.CS = CS;
        this.playedMatches = playedMatches;
        this.avgKills = avgKills;
        this.avgDeaths = avgDeaths;
        this.avgAssists = avgAssists;
        this.KDA = KDA;
        this.champUrl = champUrl;
    }

    public PlayersChampionStatsDto() {
    }

    public double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }

    public String getChampName() {
        return champName;
    }

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public double getCS() {
        return CS;
    }

    public void setCS(double CS) {
        this.CS = CS;
    }

    public int getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(int playedMatches) {
        this.playedMatches = playedMatches;
    }

    public double getAvgKills() {
        return avgKills;
    }

    public void setAvgKills(double avgKills) {
        this.avgKills = avgKills;
    }

    public double getAvgDeaths() {
        return avgDeaths;
    }

    public void setAvgDeaths(double avgDeaths) {
        this.avgDeaths = avgDeaths;
    }

    public double getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(double avgAssists) {
        this.avgAssists = avgAssists;
    }

    public double getKDA() {
        return (avgKills + avgAssists) / avgDeaths;
    }

    public String getChampUrl() {
        return champUrl;
    }

    public void setChampUrl(String champUrl) {
        this.champUrl = champUrl;
    }

    public void setKDA(double KDA) {
        this.KDA = KDA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayersChampionStatsDto that = (PlayersChampionStatsDto) o;
        return Double.compare(that.winRatio, winRatio) == 0 &&
                Double.compare(that.CS, CS) == 0 &&
                playedMatches == that.playedMatches &&
                Double.compare(that.avgKills, avgKills) == 0 &&
                Double.compare(that.avgDeaths, avgDeaths) == 0 &&
                Double.compare(that.avgAssists, avgAssists) == 0 &&
                Double.compare(that.KDA, KDA) == 0 &&
                Objects.equals(champName, that.champName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winRatio, champName, CS, playedMatches, avgKills, avgDeaths, avgAssists, KDA);
    }

    @Override
    public String toString() {
        return "PlayersChampionStatsDto{" +
                "winRatio=" + winRatio +
                ", champName='" + champName + '\'' +
                ", CS=" + CS +
                ", playedMatches=" + playedMatches +
                ", avgKill=" + avgKills +
                ", avgDeath=" + avgDeaths +
                ", avgAssists=" + avgAssists +
                ", KDA=" + KDA +
                '}';
    }
}
