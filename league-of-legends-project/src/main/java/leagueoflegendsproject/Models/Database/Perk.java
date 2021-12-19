package leagueoflegendsproject.Models.Database;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Perk")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "perkTreeMapping",
                entities = @EntityResult(
                        entityClass = Perk.class,
                        fields = {
                                @FieldResult(name = "id", column = "id"),
                                @FieldResult(name = "name", column = "name"),
                                @FieldResult(name = "icon", column = "icon"),
                                @FieldResult(name = "shortDesc", column = "short_desc"),
                                @FieldResult(name = "longDesc", column = "long_desc"),
                                @FieldResult(name = "slotNumber", column = "slot_number"),
                                @FieldResult(name = "treeNumber", column = "tree_number")
                        }
                )
        )
})
public class Perk {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "long_desc")
    private String longDesc;

    @Column(name = "slot_number")
    private Integer slotNumber;

    @Column(name = "tree_number")
    private Integer treeNumber;

    @OneToMany(mappedBy = "perk")
    private Set<MatchParticipantPerk> matchParticipantPerkSet = new HashSet<>();

    public Perk() {
    }

    public Perk(Integer id, String name, String icon, String shortDesc, String longDesc, Integer slotNumber,
                Set<MatchParticipantPerk> matchParticipantPerkSet,
                Integer treeNumber) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.slotNumber = slotNumber;
        this.treeNumber = treeNumber;
        this.matchParticipantPerkSet = matchParticipantPerkSet;
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

    public Set<MatchParticipantPerk> getMatchParticipantPerkSet() {
        return matchParticipantPerkSet;
    }

    public void setMatchParticipantPerkSet(Set<MatchParticipantPerk> matchParticipantPerkSet) {
        this.matchParticipantPerkSet = matchParticipantPerkSet;
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
        Perk perk = (Perk) o;
        return Objects.equals(id, perk.id) &&
                Objects.equals(name, perk.name) &&
                Objects.equals(icon, perk.icon) &&
                Objects.equals(shortDesc, perk.shortDesc) &&
                Objects.equals(longDesc, perk.longDesc) &&
                Objects.equals(slotNumber, perk.slotNumber) &&
                Objects.equals(treeNumber, perk.treeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, icon, shortDesc, longDesc, slotNumber, treeNumber);
    }
}
