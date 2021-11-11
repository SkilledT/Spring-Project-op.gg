package leagueoflegendsproject.Models.Database.Keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BanKey implements Serializable {

    @Embedded
    private MatchTeamKey matchTeamKey = new MatchTeamKey();

    private Integer championId;

    public BanKey(MatchTeamKey matchTeamKey, Integer championId) {
        this.matchTeamKey = matchTeamKey;
        this.championId = championId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanKey banKey = (BanKey) o;
        return Objects.equals(matchTeamKey, banKey.matchTeamKey) &&
                Objects.equals(championId, banKey.championId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchTeamKey, championId);
    }
}
