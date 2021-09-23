package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeague;

public class SummonersLeagueDto {
    private String tier;
    private String rank;
    private String summonerName;
    private long leaguePoints;
    private long wins;
    private long loses;
    private double winRatio;

    public SummonersLeagueDto(String tier, String rank, String summonerName, long leaguePoints, long wins, long loses) {
        this.tier = tier;
        this.rank = rank;
        this.summonerName = summonerName;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.loses = loses;
    }

    public SummonersLeagueDto() {
    }

    public SummonersLeagueDto(SummonerLeague summonerLeague){
        var summoner =  summonerLeague.getResponse().stream()
                .filter(e -> e.getQueueType().equals("RANKED_SOLO_5x5"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("This summoner does not participate in SoloQ"));
        this.tier = summoner.getTier();
        this.rank = summoner.getRank();
        this.summonerName = summoner.getSummonerName();
        this.leaguePoints = summoner.getLeaguePoints();
        this.wins = summoner.getWins();
        this.loses = summoner.getLosses();
        long playedMatches = wins + loses;
        this.winRatio = (double) wins / (double) playedMatches;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public long getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(long leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getLoses() {
        return loses;
    }

    public void setLoses(long loses) {
        this.loses = loses;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }
}
