package leagueoflegendsproject.Models.Database.Keys;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchTeamKey implements Serializable {

    private String matchId;
    private Integer teamId;

    public MatchTeamKey(String matchId, Integer teamId) {
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchTeamKey that = (MatchTeamKey) o;
        return Objects.equals(matchId, that.matchId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teamId);
    }
}
