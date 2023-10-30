package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class TeamsItem {

    @SerializedName("teamId")
    private int teamId;

    @SerializedName("bans")
    private List<BansItem> bans;

    @SerializedName("objectives")
    private Objectives objectives;

    @SerializedName("win")
    private boolean win;

    @Override
    public String toString() {
        return
                "TeamsItem{" +
                        "teamId = '" + teamId + '\'' +
                        ",bans = '" + bans + '\'' +
                        ",objectives = '" + objectives + '\'' +
                        ",win = '" + win + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamsItem teamsItem = (TeamsItem) o;
        return teamId == teamsItem.teamId &&
                win == teamsItem.win &&
                Objects.equals(bans, teamsItem.bans) &&
                Objects.equals(objectives, teamsItem.objectives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, bans, objectives, win);
    }
}