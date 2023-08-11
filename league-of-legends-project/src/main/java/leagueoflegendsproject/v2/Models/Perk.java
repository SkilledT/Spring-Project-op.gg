package leagueoflegendsproject.v2.Models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Perk {

    @Id
    private Long id;

    private String key;
    private String name;
    private String icon;
    private String shortDesc;
    private String longDesc;
    private Integer slotNumber;
    private Integer treeNumber;

    @OneToMany(mappedBy = "perk", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipantPerk> matchParticipantPerks = new HashSet<>();

}
