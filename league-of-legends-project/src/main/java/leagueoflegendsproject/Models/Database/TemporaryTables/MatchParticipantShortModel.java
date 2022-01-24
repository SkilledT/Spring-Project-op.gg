package leagueoflegendsproject.Models.Database.TemporaryTables;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Match;
import leagueoflegendsproject.Models.Database.ParticipantItems;
import leagueoflegendsproject.Models.Database.Summoner;
import leagueoflegendsproject.Models.Database.Team;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class MatchParticipantShortModel {
    Team team;
    Match match;
    Summoner summoner;
    Champion champion;
    Boolean win;
    Integer Kills;
    Integer deaths;
    Integer assists;
    Integer totalMinionsKilled;
    Integer champLvl;
    String championName;
    String individualPosition;
    Integer summoner1Id;
    Integer summoner2Id;
    Integer visionWardsBoughtInGame;
    Set<ParticipantItems> participantItemsSet = new HashSet<>();


    public MatchParticipantShortModel(Team team,
                                      Match match,
                                      Summoner summoner,
                                      Champion champion,
                                      Boolean win,
                                      Integer Kills,
                                      Integer deaths,
                                      Integer assists,
                                      Integer totalMinionsKilled,
                                      Integer champLvl,
                                      String championName,
                                      String individualPosition,
                                      Integer summoner1Id,
                                      Integer summoner2Id,
                                      Integer visionWardsBoughtInGame,
                                      Set<ParticipantItems> participantItemsSet){
        this.team = team;
        this.match = match;
        this.summoner = summoner;
        this.champion = champion;
        this.win = win;
        this.Kills = Kills;
        this.deaths = deaths;
        this.assists = assists;
        this.totalMinionsKilled = totalMinionsKilled;
        this.champLvl = champLvl;
        this.championName = championName;
        this.individualPosition = individualPosition;
        this.summoner1Id = summoner1Id;
        this.summoner2Id = summoner2Id;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
        this.participantItemsSet = participantItemsSet;
    }

    public MatchParticipantShortModel(Team team, Match match, Summoner summoner, Champion champion, Boolean win, Integer kills, Integer deaths, Integer assists, Integer totalMinionsKilled, Integer champLvl, String championName, String individualPosition, Integer summoner1Id, Integer summoner2Id, Integer visionWardsBoughtInGame) {
        this.team = team;
        this.match = match;
        this.summoner = summoner;
        this.champion = champion;
        this.win = win;
        Kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.totalMinionsKilled = totalMinionsKilled;
        this.champLvl = champLvl;
        this.championName = championName;
        this.individualPosition = individualPosition;
        this.summoner1Id = summoner1Id;
        this.summoner2Id = summoner2Id;
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public Integer getKills() {
        return Kills;
    }

    public void setKills(Integer kills) {
        Kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(Integer totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public Integer getChampLvl() {
        return champLvl;
    }

    public void setChampLvl(Integer champLvl) {
        this.champLvl = champLvl;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getIndividualPosition() {
        return individualPosition;
    }

    public void setIndividualPosition(String individualPosition) {
        this.individualPosition = individualPosition;
    }

    public Integer getSummoner1Id() {
        return summoner1Id;
    }

    public void setSummoner1Id(Integer summoner1Id) {
        this.summoner1Id = summoner1Id;
    }

    public Integer getSummoner2Id() {
        return summoner2Id;
    }

    public void setSummoner2Id(Integer summoner2Id) {
        this.summoner2Id = summoner2Id;
    }

    public Integer getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }

    public void setVisionWardsBoughtInGame(Integer visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }

    public Set<ParticipantItems> getParticipantItemsSet() {
        return participantItemsSet;
    }

    public void setParticipantItemsSet(Set<ParticipantItems> participantItemsSet) {
        this.participantItemsSet = participantItemsSet;
    }
}
