package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchParticipantKey implements Serializable {

    private String summonerId = new String();
    private String matchId = new String();

    public MatchParticipantKey(String summonerId, String matchId) {
        this.summonerId = summonerId;
        this.matchId = matchId;
    }

    public MatchParticipantKey() {
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchParticipantKey that = (MatchParticipantKey) o;
        return Objects.equals(summonerId, that.summonerId) &&
                Objects.equals(matchId, that.matchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summonerId, matchId);
    }
}
