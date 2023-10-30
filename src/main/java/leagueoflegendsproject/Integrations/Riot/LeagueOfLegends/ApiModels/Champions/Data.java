package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Champions;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Data {

    @SerializedName("type")
    private String type;

    @SerializedName("format")
    private String format;

    @SerializedName("version")
    private String version;

    private List<ChampionItem> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ChampionItem> getData() {
        return data;
    }

    public void setData(List<ChampionItem> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data1 = (Data) o;
        return Objects.equals(type, data1.type) &&
                Objects.equals(format, data1.format) &&
                Objects.equals(version, data1.version) &&
                Objects.equals(data, data1.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, format, version, data);
    }

    @Override
    public String toString() {
        return "Data{" +
                "type='" + type + '\'' +
                ", format='" + format + '\'' +
                ", version='" + version + '\'' +
                ", data=" + data +
                '}';
    }
}
