package leagueoflegendsproject.Helpers.TestUtils;

import leagueoflegendsproject.Models.Database.Perk;

import java.util.HashSet;

public class PerkBuilder {
    private Perk perk;

    public PerkBuilder(){
        perk = new Perk(1, "perkName", "icon.jpg", "desc", "descLong", 0, new HashSet<>(), 0);
    }

    public PerkBuilder withId(Integer id){
        this.perk.setId(id);
        return this;
    }

    public PerkBuilder withName(String name){
        this.perk.setName(name);
        return this;
    }

    public PerkBuilder withIcon(String icon){
        this.perk.setIcon(icon);
        return this;
    }

    public PerkBuilder withShortDescription(String desc){
        this.perk.setShortDesc(desc);
        return this;
    }

    public PerkBuilder withLongDescription(String desc){
        this.perk.setLongDesc(desc);
        return this;
    }

    public PerkBuilder withSlotNumber(Integer slotNumber){
        this.perk.setSlotNumber(slotNumber);
        return this;
    }

    public PerkBuilder withTreeNumber(Integer treeNumber){
        this.perk.setTreeNumber(treeNumber);
        return this;
    }

    public Perk build(){
        return  perk;
    }
}
