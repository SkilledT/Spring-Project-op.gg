package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Champions;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChampionItem {

    @SerializedName("id")
    private String id;

    @SerializedName("key")
    private String key;

    @SerializedName("version")
    private String version;

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("blurb")
    private String blurb;

    @SerializedName("partype")
    private String partype;

    private Info info;

    private Image image;

    private List<String> tags;

    private Stats stats;

    public ChampionItem(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
