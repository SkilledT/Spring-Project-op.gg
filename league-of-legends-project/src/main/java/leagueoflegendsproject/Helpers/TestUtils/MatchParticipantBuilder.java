package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import leagueoflegendsproject.Models.Database.Match;
import leagueoflegendsproject.Models.Database.MatchParticipant;

public class MatchParticipantBuilder {
    private MatchParticipant matchParticipant;

    public MatchParticipantBuilder() {
        matchParticipant = new MatchParticipant();
    }

    public MatchParticipantBuilder withIndividualPosition(Constants.MatchParticipantConstants.IndividualPosition position){
        this.matchParticipant.setIndividualPosition(position);
        return this;
    }

    public MatchParticipantBuilder isWon(boolean isWin) {
        this.matchParticipant.setWin(isWin);
        return this;
    }

    public MatchParticipantBuilder withChampionName(String championName, Integer championId) {
        this.matchParticipant.setChampion(new Champion(championId, championName));
        return this;
    }

    public MatchParticipantBuilder withKills(int kills) {
        this.matchParticipant.setKills(kills);
        return this;
    }

    public MatchParticipantBuilder withDeaths(int deaths) {
        this.matchParticipant.setDeaths(deaths);
        return this;
    }

    public MatchParticipantBuilder withAssists(int assists) {
        this.matchParticipant.setAssists(assists);
        return this;
    }

    public MatchParticipantBuilder withTotalMinionsKilled(int totalMinionsKilled) {
        this.matchParticipant.setTotalMinionsKilled(totalMinionsKilled);
        return this;
    }

    public MatchParticipantBuilder withMatch(Match match) {
        this.matchParticipant.setMatch(match);
        return this;
    }

    public MatchParticipant build() {
        return this.matchParticipant;
    }

}
