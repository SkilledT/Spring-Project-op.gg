package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamObjectiveKey implements Serializable {

    private String objectiveId;

    @Embedded
    private MatchTeamKey matchTeamKey = new MatchTeamKey();

    public TeamObjectiveKey() {
    }

    public TeamObjectiveKey(String objectiveId, MatchTeamKey matchTeamKey) {
        this.objectiveId = objectiveId;
        this.matchTeamKey = matchTeamKey;
    }

    public String getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(String objectiveId) {
        this.objectiveId = objectiveId;
    }

    public MatchTeamKey getMatchTeamKey() {
        return matchTeamKey;
    }

    public void setMatchTeamKey(MatchTeamKey matchTeamKey) {
        this.matchTeamKey = matchTeamKey;
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

    @Override
    public String toString() {
        return "TeamObjectiveKey{" +
                "objectiveId='" + objectiveId + '\'' +
                ", matchTeamKey=" + matchTeamKey +
                '}';
    }
}
