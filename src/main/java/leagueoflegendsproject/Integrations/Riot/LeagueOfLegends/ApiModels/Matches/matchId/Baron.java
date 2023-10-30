package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class Baron {

    @SerializedName("kills")
    private int kills;

    @SerializedName("first")
    private boolean first;

    @Override
    public String toString() {
        return
                "Baron{" +
                        "kills = '" + kills + '\'' +
                        ",first = '" + first + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Baron baron = (Baron) o;
        return kills == baron.kills &&
                first == baron.first;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kills, first);
    }
}