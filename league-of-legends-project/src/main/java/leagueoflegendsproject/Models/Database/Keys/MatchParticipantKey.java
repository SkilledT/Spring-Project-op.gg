package leagueoflegendsproject.Models.Database.Keys;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchParticipantKey implements Serializable {

    private String summonerId;
    private String matchId;

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
