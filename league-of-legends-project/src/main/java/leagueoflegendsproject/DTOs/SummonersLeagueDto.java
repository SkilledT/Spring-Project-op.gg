package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.Summoner;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeague;
import leagueoflegendsproject.Models.LoLApi.League.EncryptedSummonerId.SummonerLeagueResponseItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Data
public class SummonersLeagueDto {
    private String tier;
    private String rank;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int loses;
    private double winRatio;
    private String iconLink;

    public SummonersLeagueDto(String tier, String rank, String summonerName, int leaguePoints, int wins, int loses) {
        this.tier = tier;
        this.rank = rank;
        this.summonerName = summonerName;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.loses = loses;
    }

    public SummonersLeagueDto() {
    }

    public SummonersLeagueDto(SummonerLeagueResponseItem[] summonerLeague){
        var summoner = Arrays.stream(summonerLeague)
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

    public SummonersLeagueDto(Summoner summoner) {
        this.tier = summoner.getTier();
        this.rank = summoner.getRank();
        this.summonerName = summoner.getSummonerNickname();
        this.leaguePoints = summoner.getLeaguePoints();
        this.wins = summoner.getWins();
        this.loses = summoner.getLosses();
        long playedMatches = wins + loses;
        this.winRatio = (double) wins / (double) playedMatches;
        this.iconLink = RiotLinksProvider.SummonerLinksProvider.getRiotProfileIconUrl(summoner.getProfileIconId());
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

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummonersLeagueDto that = (SummonersLeagueDto) o;
        return leaguePoints == that.leaguePoints && wins == that.wins && loses == that.loses && Double.compare(that.winRatio, winRatio) == 0 && Objects.equals(tier, that.tier) && Objects.equals(rank, that.rank) && Objects.equals(summonerName, that.summonerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tier, rank, summonerName, leaguePoints, wins, loses, winRatio);
    }
}
