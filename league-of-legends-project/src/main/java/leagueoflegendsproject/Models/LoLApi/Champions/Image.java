package leagueoflegendsproject.Models.LoLApi.Champions;

import java.util.Objects;

public class Image {

    private String full;
    private String sprite;
    private String group;

    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;

    public Image() {
    }

    public Image(String full, String sprite, String group, Integer x, Integer y, Integer w, Integer h) {
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(full, image.full) &&
                Objects.equals(sprite, image.sprite) &&
                Objects.equals(group, image.group) &&
                Objects.equals(x, image.x) &&
                Objects.equals(y, image.y) &&
                Objects.equals(w, image.w) &&
                Objects.equals(h, image.h);
    }

    @Override
    public String toString() {
        return "Image{" +
                "full='" + full + '\'' +
                ", sprite='" + sprite + '\'' +
                ", group='" + group + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(full, sprite, group, x, y, w, h);
    }
}
