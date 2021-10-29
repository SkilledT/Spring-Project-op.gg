package leagueoflegendsproject.Models.Database.Keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamObjectiveKey implements Serializable {

    private String objectiveId;

    private Long teamId;

    public TeamObjectiveKey() {
    }

    public TeamObjectiveKey(String objectiveId, Long teamId) {
        this.objectiveId = objectiveId;
        this.teamId = teamId;
    }

    public String getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(String objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamObjectiveKey that = (TeamObjectiveKey) o;
        return Objects.equals(objectiveId, that.objectiveId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectiveId, teamId);
    }
}
