package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.Champion.Champion;

import java.util.Objects;

public class ChampionShortDto {
    private String title;
    private String championName;
    private String difficulty;
    private String iconUrl;

    public ChampionShortDto() {
    }

    public ChampionShortDto(Champion champion) {
        this.title = champion.getTitle();
        this.championName = champion.getName();
        this.iconUrl = RiotLinksProvider.ChampionLinkProvider.getSlashImg(champion.getImage() != null ? champion.getImage().getFull() : "null");
        int champDiff = champion.getInfo() != null ? champion.getInfo().getDifficulty() : 0;
        if (champDiff >= 8){
            this.difficulty = "Severe";
        } else if (champDiff >= 5) {
            this.difficulty = "Average";
        } else {
            this.difficulty = "Easy";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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
        ChampionShortDto that = (ChampionShortDto) o;
        return Objects.equals(title, that.title) && Objects.equals(championName, that.championName) && Objects.equals(difficulty, that.difficulty) && Objects.equals(iconUrl, that.iconUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, championName, difficulty, iconUrl);
    }
}
