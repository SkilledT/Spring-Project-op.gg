package leagueoflegendsproject.Models.Database;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perk {

    @Id
    @Column(name = "id")
    @SerializedName(value = "id")
    private Integer id;

    @Column(
            name = "name",
            length = 10000,
            unique = true,
            insertable = true,
            updatable = true,
            nullable = false
    )
    @SerializedName(value = "name")
    private String name;

    @Column(name = "icon")
    @SerializedName(value = "icon")
    private String icon;

    @Column(name = "short_desc", length = 10000)
    @SerializedName(value = "short_desc")
    private String shortDesc;

    @Column(name = "long_desc", length = 10000)
    @SerializedName(value = "long_desc")
    private String longDesc;

    @Column(name = "slot_number")
    @SerializedName(value = "slot_number")
    private Integer slotNumber;

    @Column(name = "tree_number")
    @SerializedName(value = "tree_number")
    private Integer treeNumber;

    @OneToMany(mappedBy = "perk", cascade = CascadeType.ALL)
    private Set<MatchParticipantPerk> matchParticipantPerkSet = new HashSet<>();

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

    public Perk(Integer id, String name, String icon, String shortDesc, String longDesc, Integer slotNumber, Integer treeNumber) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.slotNumber = slotNumber;
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

    @Override
    public String toString() {
        return "Perk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", slotNumber=" + slotNumber +
                ", treeNumber=" + treeNumber +
                '}';
    }
}
