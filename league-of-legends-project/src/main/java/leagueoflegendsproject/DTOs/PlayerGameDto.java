package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
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
        this.championUrl = RiotLinksProvider.ChampionLinkProvider.getIconImg(champName);
    }

    public PlayerGameDto(ParticipantsItem participantsItem){
        this(participantsItem.getChampionName(), participantsItem.getSummonerName(), participantsItem.getPuuid());
        this.championUrl = RiotLinksProvider.ChampionLinkProvider.getIconImg(champName);
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
