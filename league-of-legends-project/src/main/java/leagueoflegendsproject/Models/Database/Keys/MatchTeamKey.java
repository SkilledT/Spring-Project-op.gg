package leagueoflegendsproject.Models.Database.Keys;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MatchTeamKey implements Serializable {

    private String matchId;

    private Long teamId;

    public MatchTeamKey(String matchId, Long teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public MatchTeamKey() {
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
