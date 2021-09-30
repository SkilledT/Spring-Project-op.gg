package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;

public class PlayerGameDto {
    private String champName;
    private String nickname;
    private String puuid;
    private String championUrl;

    public PlayerGameDto(String champName, String nickname, String puuid) {
        this.champName = champName;
        this.nickname = nickname;
        this.puuid = puuid;
        this.championUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/"+champName+".png";
    }

    public PlayerGameDto(ParticipantsItem participantsItem){
        this.champName = participantsItem.getChampionName();
        this.nickname = participantsItem.getSummonerName();
        this.puuid = participantsItem.getPuuid();
        this.championUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/"+participantsItem.getChampionName()+".png";
    }

    public PlayerGameDto() {
    }

    public String getChampName() {
        return champName;
    }

    public void setChampName(String champName) {
        this.champName = champName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getChampionUrl() {
        return championUrl;
    }

    public void setChampionUrl(String championUrl) {
        this.championUrl = championUrl;
    }
}
