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
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalMatchId;
    private Integer gameId;
    private String gameType;
    private Integer queueId;
    private Integer gameDuration;
    private Integer gameStartTimestamp;
    private String platformId;
    private Long gameCreation;
    private Integer mapId;
    private String gameMode;
    private String apiVersion;


    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Team> teams = new HashSet<>();

}
