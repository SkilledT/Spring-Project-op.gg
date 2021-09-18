package leagueoflegendsproject.DTOs;

import java.util.List;

public class MatchDetailsDto {
    private String championName;
    private long timeDurationOfMatch;
    private String championIconUrl;
    private long summoner1Id;
    private long summoner2Id;
    private int kills;
    private int deaths;
    private int assists;
    private int champLvl;
    private int killedMinions;
    private double pInKill;
    private List<ItemMatchDto> list;
    private int controlWardsPurchased;
    private List<PlayerGameDto> allies;
    private List<PlayerGameDto> enemies;
    private boolean isWin;

    public MatchDetailsDto(String championName,
                           long timeDurationOfMatch,
                           String championIconUrl,
                           long summoner1Id,
                           long summoner2Id,
                           int kills,
                           int deaths,
                           int assists,
                           int champLvl,
                           int killedMinions,
                           double pInKill,
                           List<ItemMatchDto> list,
                           int controlWardsPurchased,
                           List<PlayerGameDto> allies,
                           List<PlayerGameDto> enemies,
                           boolean isWin) {
        this.championName = championName;
        this.timeDurationOfMatch = timeDurationOfMatch;
        this.championIconUrl = championIconUrl;
        this.summoner1Id = summoner1Id;
        this.summoner2Id = summoner2Id;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.champLvl = champLvl;
        this.killedMinions = killedMinions;
        this.pInKill = pInKill;
        this.list = list;
        this.controlWardsPurchased = controlWardsPurchased;
        this.allies = allies;
        this.enemies = enemies;
        this.isWin = isWin;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public long getTimeDurationOfMatch() {
        return timeDurationOfMatch;
    }

    public void setTimeDurationOfMatch(long timeDurationOfMatch) {
        this.timeDurationOfMatch = timeDurationOfMatch;
    }

    public String getChampionIconUrl() {
        return championIconUrl;
    }

    public void setChampionIconUrl(String championIconUrl) {
        this.championIconUrl = championIconUrl;
    }

    public long getSummoner1Id() {
        return summoner1Id;
    }

    public void setSummoner1Id(long summoner1Id) {
        this.summoner1Id = summoner1Id;
    }

    public long getSummoner2Id() {
        return summoner2Id;
    }

    public void setSummoner2Id(long summoner2Id) {
        this.summoner2Id = summoner2Id;
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

    public int getChampLvl() {
        return champLvl;
    }

    public void setChampLvl(int champLvl) {
        this.champLvl = champLvl;
    }

    public int getKilledMinions() {
        return killedMinions;
    }

    public void setKilledMinions(int killedMinions) {
        this.killedMinions = killedMinions;
    }

    public double getpInKill() {
        return pInKill;
    }

    public void setpInKill(double pInKill) {
        this.pInKill = pInKill;
    }

    public List<ItemMatchDto> getList() {
        return list;
    }

    public void setList(List<ItemMatchDto> list) {
        this.list = list;
    }

    public int getControlWardsPurchased() {
        return controlWardsPurchased;
    }

    public void setControlWardsPurchased(int controlWardsPurchased) {
        this.controlWardsPurchased = controlWardsPurchased;
    }

    public List<PlayerGameDto> getAllies() {
        return allies;
    }

    public void setAllies(List<PlayerGameDto> allies) {
        this.allies = allies;
    }

    public List<PlayerGameDto> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<PlayerGameDto> enemies) {
        this.enemies = enemies;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
