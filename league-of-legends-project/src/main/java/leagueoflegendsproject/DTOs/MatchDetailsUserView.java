package leagueoflegendsproject.DTOs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MatchDetailsUserView {
    int teamId;
    MatchUserView matchUserView;
    String championName;
    Set<Integer> itemIds;
    String summonerNickname;
    String summonerId;
    int kills;
    int deaths;
    int assists;

    public MatchDetailsUserView(int teamId, MatchUserView matchUserView, String championName, Set<Integer> itemIds, String summonerNickname, String summonerId, int kills, int deaths, int assists) {
        this.teamId = teamId;
        this.matchUserView = matchUserView;
        this.championName = championName;
        this.itemIds = itemIds;
        this.summonerNickname = summonerNickname;
        this.summonerId = summonerId;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public MatchUserView getMatchUserView() {
        return matchUserView;
    }

    public void setMatchUserView(MatchUserView matchUserView) {
        this.matchUserView = matchUserView;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public Set<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(Set<Integer> itemIds) {
        this.itemIds = itemIds;
    }

    public String getSummonerNickname() {
        return summonerNickname;
    }

    public void setSummonerNickname(String summonerNickname) {
        this.summonerNickname = summonerNickname;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchDetailsUserView that = (MatchDetailsUserView) o;
        return teamId == that.teamId && kills == that.kills && deaths == that.deaths && assists == that.assists && Objects.equals(matchUserView, that.matchUserView) && Objects.equals(championName, that.championName) && Objects.equals(itemIds, that.itemIds) && Objects.equals(summonerNickname, that.summonerNickname) && Objects.equals(summonerId, that.summonerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, matchUserView, championName, itemIds, summonerNickname, summonerId, kills, deaths, assists);
    }
}


class MatchUserView {
    Set<MatchDetailsUserView> matchDetailsUserViews = new HashSet<>();

}