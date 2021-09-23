package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;

public class PlayerGameDto {
    private String champName;
    private String nickname;
    private String puuid;

    public PlayerGameDto(String champName, String nickname, String puuid) {
        this.champName = champName;
        this.nickname = nickname;
        this.puuid = puuid;
    }

    public PlayerGameDto(ParticipantsItem participantsItem){
        this.champName = participantsItem.getChampionName();
        this.nickname = participantsItem.getSummonerName();
        this.puuid = participantsItem.getPuuid();
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
}
