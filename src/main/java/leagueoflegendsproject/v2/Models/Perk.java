package leagueoflegendsproject.v2.Models;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;
    private Integer externalId;
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
