package leagueoflegendsproject.DTOs;

import java.util.List;

public class LastMatchesDto {
    private int playedGames;
    private double avgKills;
    private double avgDeaths;
    private double avgAssists;
    private double avgPInKill;
    private double avgKDA;
    private List<PreferedRoleDto> roles;
    private long victories;
    private long defeats;

    public static final class Builder{
        private int playedGames;
        private double avgKills;
        private double avgDeaths;
        private double avgAssists;
        private double avgPInKill;
        private double avgKDA;
        private List<PreferedRoleDto> roles;
        private long victories;
        private long defeats;

        public Builder playedGames(int playedGames){
            this.playedGames = playedGames;
            return this;
        }

        public Builder victories(long victories){
            this.victories = victories;
            return this;
        }

        public Builder defeats(long defeats){
            this.defeats = defeats;
            return this;
        }

        public Builder avgKills(double avgKills){
            this.avgKills = avgKills;
            return this;
        }
        public Builder avgDeaths(double avgDeaths){
            this.avgDeaths = avgDeaths;
            return this;
        }
        public Builder avgAssists(double avgAssists){
            this.avgAssists = avgAssists;
            return this;
        }
        public Builder avgPInKill(double avgPInKill){
            this.avgPInKill = avgPInKill;
            return this;
        }
        public Builder avgKDA(double avgKDA){
            this.avgKDA = avgKDA;
            return this;
        }
        public Builder roles(List<PreferedRoleDto> roles){
            this.roles = roles;
            return this;
        }

        public LastMatchesDto build(){
            LastMatchesDto lastMatchesDto = new LastMatchesDto();
            lastMatchesDto.playedGames = this.playedGames;
            lastMatchesDto.avgKills = this.avgKills;
            lastMatchesDto.avgDeaths = this.avgDeaths;
            lastMatchesDto.avgAssists = this.avgAssists;
            lastMatchesDto.avgKDA = this.avgKDA;
            lastMatchesDto.roles = this.roles;
            lastMatchesDto.victories = this.victories;
            lastMatchesDto.defeats = this.defeats;
            return lastMatchesDto;
        }
    }

    private LastMatchesDto() {
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public double getAvgKills() {
        return avgKills;
    }

    public double getAvgDeaths() {
        return avgDeaths;
    }

    public double getAvgAssists() {
        return avgAssists;
    }

    public double getAvgPInKill() {
        return avgPInKill;
    }

    public double getAvgKDA() {
        return avgKDA;
    }

    public List<PreferedRoleDto> getRoles() {
        return roles;
    }

    public long getDefeats() {
        return defeats;
    }

    public long getVictories() {
        return victories;
    }
}
