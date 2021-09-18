package leagueoflegendsproject.DTOs;

public class PlayersChampionStatsDto {
    private double winRatio;
    private String champName;
    private double CS;
    private int playedMatches;
    private double avgKill;
    private double avgDeath;
    private double avgAssists;
    private double KDA;

    public PlayersChampionStatsDto(double winRatio,
                                   String champName,
                                   double CS,
                                   int playedMatches,
                                   double avgKill,
                                   double avgDeath,
                                   double avgAssists) {
        this.winRatio = winRatio;
        this.champName = champName;
        this.CS = CS;
        this.playedMatches = playedMatches;
        this.avgKill = avgKill;
        this.avgDeath = avgDeath;
        this.avgAssists = avgAssists;
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

    public double getAvgKill() {
        return avgKill;
    }

    public void setAvgKill(double avgKill) {
        this.avgKill = avgKill;
    }

    public double getAvgDeath() {
        return avgDeath;
    }

    public void setAvgDeath(double avgDeath) {
        this.avgDeath = avgDeath;
    }

    public double getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(double avgAssists) {
        this.avgAssists = avgAssists;
    }

    public double getKDA() {
        return (avgKill + avgAssists)/avgDeath;
    }
}
