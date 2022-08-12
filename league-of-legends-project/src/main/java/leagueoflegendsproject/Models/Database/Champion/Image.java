package leagueoflegendsproject.Models.Database.Champion;

import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Image")
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "`full`", length = 10000)
    private String full;
    @Column(name = "sprite", length = 10000)
    private String sprite;
    @Column(name = "`group`", length = 10000)
    private String group;

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private Set<Champion> championSet = new HashSet<>();

    public Image(ChampionItem championItem){
        this.full = championItem.getImage().getFull();
        this.sprite = championItem.getImage().getSprite();
        this.group = championItem.getImage().getGroup();
        this.x = championItem.getImage().getX();
        this.y = championItem.getImage().getY();
        this.w = championItem.getImage().getW();
        this.h = championItem.getImage().getH();
    }

    public Image(Integer id, String full, String sprite, String group, Integer x, Integer y, Integer w, Integer h, Set<Champion> championSet) {
        this.id = id;
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.championSet = championSet;
    }

    public Image() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Set<Champion> getChampionSet() {
        return championSet;
    }

    public void setChampionSet(Set<Champion> championSet) {
        this.championSet = championSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) &&
                Objects.equals(full, image.full) &&
                Objects.equals(sprite, image.sprite) &&
                Objects.equals(group, image.group) &&
                Objects.equals(x, image.x) &&
                Objects.equals(y, image.y) &&
                Objects.equals(w, image.w) &&
                Objects.equals(h, image.h);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, full, sprite, group, x, y, w, h);
    }
}
