package leagueoflegendsproject.Models.Database.Keys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TeamObjectiveKey implements Serializable {

    private String objectiveId;

    @Embedded
    private MatchTeamKey matchTeamKey;

    public TeamObjectiveKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamObjectiveKey that = (TeamObjectiveKey) o;
        return Objects.equals(objectiveId, that.objectiveId) &&
                Objects.equals(matchTeamKey, that.matchTeamKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectiveId, matchTeamKey);
    }
}
