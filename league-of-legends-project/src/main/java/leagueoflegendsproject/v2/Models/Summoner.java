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
public class Summoner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summonerId;
    private Integer lvl;
    private Integer profileIconId;

    @OneToMany(mappedBy = "summoner", fetch = FetchType.LAZY)
    private Set<SummonerSnapshot> summonerSnapshots = new HashSet<>();
}
