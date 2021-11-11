package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipantItemsKey implements Serializable {

    private MatchParticipantKey matchParticipantKey = new MatchParticipantKey();

    public ParticipantItemsKey(MatchParticipantKey matchParticipantKey) {
        this.matchParticipantKey = matchParticipantKey;
    }

    public ParticipantItemsKey() {
    }

    public MatchParticipantKey getMatchParticipantKey() {
        return matchParticipantKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantItemsKey that = (ParticipantItemsKey) o;
        return Objects.equals(matchParticipantKey, that.matchParticipantKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantKey);
    }

    public void setMatchParticipantKey(MatchParticipantKey matchParticipantKey) {
        this.matchParticipantKey = matchParticipantKey;
    }
}
