package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipantItemsKey implements Serializable {

    @Embedded
    private MatchParticipantKey matchParticipantKey;

    private Long itemId;

    public ParticipantItemsKey() {
    }

    public ParticipantItemsKey(MatchParticipantKey matchParticipantKey, Long itemId) {
        this.matchParticipantKey = matchParticipantKey;
        this.itemId = itemId;
    }

    public MatchParticipantKey getMatchParticipantKey() {
        return matchParticipantKey;
    }

    public void setMatchParticipantKey(MatchParticipantKey matchParticipantKey) {
        this.matchParticipantKey = matchParticipantKey;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantItemsKey that = (ParticipantItemsKey) o;
        return Objects.equals(matchParticipantKey, that.matchParticipantKey) &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchParticipantKey, itemId);
    }
}
