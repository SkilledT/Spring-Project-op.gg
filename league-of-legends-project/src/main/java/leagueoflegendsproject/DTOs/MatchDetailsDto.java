package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.NumericalHelpers;

import java.util.List;

public class MatchDetailsDto {
    private String championName;
    private String championUrl;
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
    private List<ItemMatchDto> items;
    private int controlWardsPurchased;
    private List<PlayerGameDto> allies;
    private List<PlayerGameDto> enemies;
    private boolean isWin;
    private String position;
    private String spell1Url;
    private String spell2Url;


    public static final class Builder{
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
        private List<ItemMatchDto> items;
        private int controlWardsPurchased;
        private List<PlayerGameDto> allies;
        private List<PlayerGameDto> enemies;
        private boolean isWin;
        private String position;

        public Builder championName(String champName){
            this.championName = champName;
            return this;
        }

        public Builder position(String position){
            this.position = position;
            return this;
        }

        public Builder timeDurationOfMatch(long timeDurationOfMatch){
            this.timeDurationOfMatch = timeDurationOfMatch;
            return this;
        }

        public Builder championIconUrl(String championIconUrl){
            this.championIconUrl = championIconUrl;
            return this;
        }

        public Builder summoner1Id(long summoner1Id){
            this.summoner1Id = summoner1Id;
            return this;
        }

        public Builder summoner2Id(long summoner2Id){
            this.summoner2Id = summoner2Id;
            return this;
        }

        public Builder kills(int kills){
            this.kills = kills;
            return this;
        }

        public Builder deaths(int deaths){
            this.deaths = deaths;
            return this;
        }

        public Builder assists(int assists){
            this.assists = assists;
            return this;
        }

        public Builder champLvl(int champLvl){
            this.champLvl = champLvl;
            return this;
        }

        public Builder killedMinions(int killedMinions){
            this.killedMinions = killedMinions;
            return this;
        }

        public Builder controlWardsPurchased(int controlWardsPurchased){
            this.controlWardsPurchased = controlWardsPurchased;
            return this;
        }

        public Builder pInKill(double pInKill){
            this.pInKill = NumericalHelpers.doubleWithTwoPlaces(pInKill);
            return this;
        }

        public Builder list(List<ItemMatchDto> list){
            this.items = list;
            return this;
        }

        public Builder allies(List<PlayerGameDto> allies){
            this.allies = allies;
            return this;
        }

        public Builder enemies(List<PlayerGameDto> enemies){
            this.enemies = enemies;
            return this;
        }

        public Builder isWin(boolean isWin){
            this.isWin = isWin;
            return this;
        }

        public MatchDetailsDto build(){
            if (championName.isEmpty())
                throw new IllegalStateException("ChampionName cannot be empty");

            MatchDetailsDto matchDetailsDto = new MatchDetailsDto();
            matchDetailsDto.championName = this.championName;
            matchDetailsDto.timeDurationOfMatch = this.timeDurationOfMatch;
            matchDetailsDto.championIconUrl = this.championIconUrl;
            matchDetailsDto.summoner1Id = this.summoner1Id;
            matchDetailsDto.summoner2Id = this.summoner2Id;
            matchDetailsDto.kills = this.kills;
            matchDetailsDto.deaths = this.deaths;
            matchDetailsDto.assists = this.assists;
            matchDetailsDto.champLvl = this.champLvl;
            matchDetailsDto.killedMinions = this.killedMinions;
            matchDetailsDto.pInKill = this.pInKill;
            matchDetailsDto.items = this.items;
            matchDetailsDto.controlWardsPurchased = this.controlWardsPurchased;
            matchDetailsDto.allies = this.allies;
            matchDetailsDto.enemies = this.enemies;
            matchDetailsDto.isWin = this.isWin;
            matchDetailsDto.position = this.position;
            matchDetailsDto.championUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/"+this.championName+".png";
            matchDetailsDto.spell1Url = getSummonerSpellIconUrl((int)this.summoner1Id);
            matchDetailsDto.spell2Url = getSummonerSpellIconUrl((int)this.summoner2Id);
            return matchDetailsDto;
        }

        private String getSummonerSpellIconUrl(Integer id){
            return "https://ddragon.leagueoflegends.com/cdn/11.22.1/img/spell/Summoner"+getRightSummonerSpellName(id)+".png";
        }

        private String getRightSummonerSpellName(Integer id){
        /*
        21: Barrier
        1: Cleanse
        14: Ignite
        3: Exhaust
        4: Flash
        6: Ghost
        7: Heal
        13: Clarity
        11: Smite
        12: Teleport
        54: Placeholder
         */
            switch (id){
                case 21:
                    return "Barrier";
                case 1:
                    return "Boost";
                case 14:
                    return "Dot";
                case 3:
                    return "Exhaust";
                case 4:
                    return "Flash";
                case 6:
                    return "Haste";
                case 7:
                    return "Heal";
                case 13:
                    return "Mana";
                case 11:
                    return "Smite";
                case 12:
                    return "Teleport";
                default:
                    return "_UltBook_Placeholder";
            }
        }

    }

    public String getChampionName() {
        return championName;
    }

    public long getTimeDurationOfMatch() {
        return timeDurationOfMatch;
    }

    public String getChampionIconUrl() {
        return championIconUrl;
    }

    public long getSummoner1Id() {
        return summoner1Id;
    }

    public long getSummoner2Id() {
        return summoner2Id;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getChampLvl() {
        return champLvl;
    }

    public int getKilledMinions() {
        return killedMinions;
    }

    public double getpInKill() {
        return pInKill;
    }

    public List<ItemMatchDto> getItems() {
        return items;
    }

    public int getControlWardsPurchased() {
        return controlWardsPurchased;
    }

    public List<PlayerGameDto> getAllies() {
        return allies;
    }

    public List<PlayerGameDto> getEnemies() {
        return enemies;
    }

    public boolean isWin() {
        return isWin;
    }

    public String getPosition() {
        return position;
    }

    public String getChampionUrl() {
        return championUrl;
    }

    public String getSpell1Url() {
        return spell1Url;
    }

    public String getSpell2Url() {
        return spell2Url;
    }
}
