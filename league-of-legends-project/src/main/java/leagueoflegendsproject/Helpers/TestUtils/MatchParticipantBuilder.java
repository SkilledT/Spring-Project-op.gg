package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.Models.Database.MatchParticipant;

public class MatchParticipantBuilder {
    private MatchParticipant matchParticipant;

    public MatchParticipantBuilder() {
        matchParticipant = new MatchParticipant();
    }

    public MatchParticipantBuilder withIndividualPosition(String position){
        this.matchParticipant.setIndividualPosition(position);
        return this;
    }

    public MatchParticipantBuilder isWon(boolean isWin) {
        this.matchParticipant.setWin(isWin);
        return this;
    }

    public MatchParticipantBuilder withLane(String lane) {
        this.matchParticipant.setLane(lane);
        return this;
    }

    public MatchParticipant build() {
        return this.matchParticipant;
    }

}
