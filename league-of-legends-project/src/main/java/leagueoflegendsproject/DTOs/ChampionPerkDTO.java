package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Models.Database.ChampionPerk;

import java.util.Objects;

public class ChampionPerkDTO {
    private Integer id;
    private String name;
    private String icon;
    private String shortDesc;
    private String longDesc;
    private Integer slotNumber;
    private Integer treeNumber;

    public ChampionPerkDTO() {
    }

    public ChampionPerkDTO(ChampionPerk championPerk) {
        this.id = championPerk.getPerk().getId();
        this.name = championPerk.getPerk().getName();
        this.icon = championPerk.getPerk().getIcon();
        this.shortDesc = championPerk.getPerk().getShortDesc();
        this.longDesc = championPerk.getPerk().getLongDesc();
        this.slotNumber = championPerk.getPerk().getSlotNumber();
        this.treeNumber = championPerk.getPerk().getTreeNumber();
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getTreeNumber() {
        return treeNumber;
    }

    public void setTreeNumber(Integer treeNumber) {
        this.treeNumber = treeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionPerkDTO that = (ChampionPerkDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(icon, that.icon) && Objects.equals(shortDesc, that.shortDesc) && Objects.equals(longDesc, that.longDesc) && Objects.equals(slotNumber, that.slotNumber) && Objects.equals(treeNumber, that.treeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, icon, shortDesc, longDesc, slotNumber, treeNumber);
    }
}
