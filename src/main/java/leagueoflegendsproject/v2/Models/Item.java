package leagueoflegendsproject.v2.Models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dbId;

    private String version;
    private Integer externalId;
    private String description;
    private String plainText;
    private Integer totalCost;
    private Integer sell;
    private Integer baseCost;
    private String name;

    @OneToMany(mappedBy = "masterComponent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ItemComponent> itemComponentSet = new HashSet<>();

    @OneToMany(mappedBy = "subComponent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<ItemComponent> itemMasterSet = new HashSet<>();

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipantItem> matchParticipantItems = new HashSet<>();

}
