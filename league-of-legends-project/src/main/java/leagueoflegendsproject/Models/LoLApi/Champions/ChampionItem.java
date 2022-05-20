package leagueoflegendsproject.Models.LoLApi.Champions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public ChampionItem(String id, String key, String version, String name, String title, String blurb, String partype, Info info, Image image, List<String> tags, Stats stats) {
        this.id = id;
        this.key = key;
        this.version = version;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
        this.partype = partype;
        this.info = info;
        this.image = image;
        this.tags = tags;
        this.stats = stats;
    }

    public ChampionItem() {
    }

    public ChampionItem(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
