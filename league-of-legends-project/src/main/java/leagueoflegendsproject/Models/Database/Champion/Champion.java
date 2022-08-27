package leagueoflegendsproject.Models.Database.Champion;


import com.google.gson.annotations.SerializedName;
import leagueoflegendsproject.Models.Database.Ban;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.Models.LoLApi.Matches.matchId.ParticipantsItem;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "Champion")
public class Champion {

    //key
    @Id
    @Column(name = "champion_id")
    @SerializedName(value = "champion_id")
    private Integer id;

    //id or name
    @Column(name = "name", length = 10000)
    @Nullable
    @SerializedName(value = "name")
    private String name;

    @Column(name = "icon_url", length = 10000)
    @Nullable
    @SerializedName(value = "icon_url")
    private String iconUrl;

    @Column(name = "title", length = 10000)
    @Nullable
    @SerializedName(value = "title")
    private String title;

    @Column(name = "blurb", length = 10000)
    @Nullable
    @SerializedName(value = "blurb")
    private String blurb;

    @OneToMany(mappedBy = "champion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "champion", cascade = CascadeType.ALL)
    private Set<Ban> ban = new HashSet<>();

    @OneToMany(mappedBy = "champion", cascade = CascadeType.ALL)
    private Set<ChampionTag> championTags = new HashSet<>();

    public void addBanChild(Ban ban) {
        this.ban.add(ban);
        ban.setChampion(this);
    }

    public void addMatchParticipantChild(MatchParticipant matchParticipant) {
        this.getMatchParticipantSet().add(matchParticipant);
        matchParticipant.setChampion(this);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id")
    @Nullable
    private Stats stats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    @Nullable
    private Info info;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    @Nullable
    private Image image;

    public Champion(ParticipantsItem participantsItem){
        this.id = participantsItem.getChampionId();
        this.name = participantsItem.getChampionName();
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/" + participantsItem.getChampionName() +".png";
    }

    public Champion(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/" + name +".png";
    }

    public Champion(Integer id, String name, String iconUrl, Set<MatchParticipant> matchParticipantSet, Set<Ban> ban) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.matchParticipantSet = matchParticipantSet;
        this.ban = ban;
    }

    public Champion() {
    }

    public Champion(ChampionItem championItem){
        this.id = Integer.parseInt(championItem.getKey());
        this.name = championItem.getName();
        this.title = championItem.getTitle();
        this.blurb = String.valueOf(championItem.getBlurb().charAt(0));
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/" + championItem.getName() +".png";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Set<MatchParticipant> getMatchParticipantSet() {
        return matchParticipantSet;
    }

    public void setMatchParticipantSet(Set<MatchParticipant> matchParticipantSet) {
        this.matchParticipantSet = matchParticipantSet;
    }

    public Set<Ban> getBan() {
        return ban;
    }

    public void setBan(Set<Ban> ban) {
        this.ban = ban;
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

    @Nullable
    public Stats getStats() {
        return stats;
    }

    public void setStats(@Nullable Stats stats) {
        this.stats = stats;
    }

    @Nullable
    public Info getInfo() {
        return info;
    }

    public void setInfo(@Nullable Info info) {
        this.info = info;
    }

    @Nullable
    public Image getImage() {
        return image;
    }

    public void setImage(@Nullable Image image) {
        this.image = image;
    }

    public Set<ChampionTag> getChampionTags() {
        return championTags;
    }

    public void setChampionTags(Set<ChampionTag> championTags) {
        this.championTags = championTags;
    }

    public void update(ParticipantsItem participantsItem){
        this.name = participantsItem.getChampionName();
        this.iconUrl = "http://ddragon.leagueoflegends.com/cdn/11.19.1/img/champion/" + participantsItem.getChampionName() + ".png";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Champion champion = (Champion) o;
        return Objects.equals(id, champion.id) &&
                Objects.equals(name, champion.name) &&
                Objects.equals(iconUrl, champion.iconUrl) &&
                Objects.equals(title, champion.title) &&
                Objects.equals(blurb, champion.blurb) &&
                Objects.equals(stats, champion.stats) &&
                Objects.equals(info, champion.info) &&
                Objects.equals(image, champion.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, iconUrl, title, blurb, stats, info, image);
    }
}
