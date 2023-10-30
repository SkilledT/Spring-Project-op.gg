package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Matches.matchId;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class Objectives {

    @SerializedName("baron")
    private Baron baron;

    @SerializedName("inhibitor")
    private Inhibitor inhibitor;

    @SerializedName("dragon")
    private Dragon dragon;

    @SerializedName("riftHerald")
    private RiftHerald riftHerald;

    @SerializedName("champion")
    private Champion champion;

    @SerializedName("tower")
    private Tower tower;

    @Override
    public String toString() {
        return
                "Objectives{" +
                        "baron = '" + baron + '\'' +
                        ",inhibitor = '" + inhibitor + '\'' +
                        ",dragon = '" + dragon + '\'' +
                        ",riftHerald = '" + riftHerald + '\'' +
                        ",champion = '" + champion + '\'' +
                        ",tower = '" + tower + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objectives that = (Objectives) o;
        return Objects.equals(baron, that.baron) &&
                Objects.equals(inhibitor, that.inhibitor) &&
                Objects.equals(dragon, that.dragon) &&
                Objects.equals(riftHerald, that.riftHerald) &&
                Objects.equals(champion, that.champion) &&
                Objects.equals(tower, that.tower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baron, inhibitor, dragon, riftHerald, champion, tower);
    }
}