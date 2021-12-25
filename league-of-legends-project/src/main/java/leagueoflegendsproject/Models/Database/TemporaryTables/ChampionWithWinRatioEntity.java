package leagueoflegendsproject.Models.Database.TemporaryTables;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ChampionWithWinRatio",
                classes = @ConstructorResult(
                        targetClass = ChampionWithWinRatioEntity.class,
                        columns = {
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "win_ratio", type = Double.class),
                                @ColumnResult(name = "icon_url", type = String.class),
                                @ColumnResult(name = "games", type = Integer.class)
                        }
                )
        )
})
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "championWithWinRatio",
                procedureName = "champion_with_win_ratio",
                parameters = {
                        @StoredProcedureParameter(name = "championName", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "minimumMatches", type = Integer.class, mode = ParameterMode.IN)
                }
        )
)
public class ChampionWithWinRatioEntity {

    @Id
    private String name;
    private Double winRatio;
    private String iconUrl;
    private Integer games;

    public ChampionWithWinRatioEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(Double winRatio) {
        this.winRatio = winRatio;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionWithWinRatioEntity that = (ChampionWithWinRatioEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(winRatio, that.winRatio) && Objects.equals(iconUrl, that.iconUrl) && Objects.equals(games, that.games);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, winRatio, iconUrl, games);
    }

    @Override
    public String toString() {
        return "ChampionWithWinRatioEntity{" +
                "name='" + name + '\'' +
                ", winRatio=" + winRatio +
                ", iconUrl='" + iconUrl + '\'' +
                ", games=" + games +
                '}';
    }
}
