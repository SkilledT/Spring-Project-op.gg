package leagueoflegendsproject.v2.Models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class SummonerSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String summonerNickname;
    private String tier;
    private String rank;
    private Integer leaguePoints;
    private Integer wins;
    private Integer losses;
    private Instant createdAt = Instant.now();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Summoner summoner;

    @OneToMany(mappedBy = "summonerSnapshot", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipant> matchParticipant = new HashSet<>();

}
