package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchParticipantPerkKey implements Serializable {

    @Embedded
    private MatchParticipantKey matchParticipantKey = new MatchParticipantKey();

    private Integer perkId;

    public MatchParticipantPerkKey() {
    }

    public MatchParticipantPerkKey(MatchParticipantKey matchParticipantKey, Integer perkId) {
        this.matchParticipantKey = matchParticipantKey;
        this.perkId = perkId;
    }

    public MatchParticipantKey getMatchParticipantKey() {
        return matchParticipantKey;
    }

    public void setMatchParticipantKey(MatchParticipantKey matchParticipantKey) {
        this.matchParticipantKey = matchParticipantKey;
    }

    public Integer getPerkId() {
        return perkId;
    }

    public void setPerkId(Integer perkId) {
        this.perkId = perkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchParticipantPerkKey that = (MatchParticipantPerkKey) o;
        return Objects.equals(matchParticipantKey, that.matchParticipantKey) &&
                Objects.equals(perkId, that.perkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantKey, perkId);
    }
}
