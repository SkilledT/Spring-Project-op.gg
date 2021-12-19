package leagueoflegendsproject.DTOs;

import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Models.Database.Perk;

public class PerkDto {

    private Integer id;
    private String name;
    private String icon;
    private String shortDesc;
    private String longDesc;
    private Integer slotNumber;
    private Integer treeNumber;

    public PerkDto(Integer id, String name, String icon, String shortDesc, String longDesc, Integer slotNumber, Integer treeNumber) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.slotNumber = slotNumber;
        this.treeNumber = treeNumber;
    }

    public PerkDto(Perk perk) {
        this.id = perk.getId();
        this.name = perk.getName();
        this.icon = RiotLinksProvider.PerkLinksProvider.getPerkIconUrl(perk);
        this.shortDesc = perk.getShortDesc();
        this.longDesc = perk.getLongDesc();
        this.slotNumber = perk.getSlotNumber();
        this.treeNumber = perk.getTreeNumber();
    }

    public PerkDto() {
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
}
