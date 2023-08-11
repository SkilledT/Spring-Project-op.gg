package leagueoflegendsproject.v2.Models;


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
public class Match {

    @Id
    private Long id;

    private String matchId;
    private Integer gameId;
    private String gameType;
    private Integer queueId;
    private Integer gameDuration;
    private Integer gameStartTimestamp;
    private String platformId;
    private Long gameCreation;
    private Integer mapId;
    private String gameMode;


    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Team> teams = new HashSet<>();

}
