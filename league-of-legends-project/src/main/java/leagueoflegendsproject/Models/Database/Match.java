package leagueoflegendsproject.Models.Database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Match")
public class Match {

    @Id
    @Column(name = "match_id")
    private String id;

    private Long gameId;

    private String gameType;

    private Long queueId;

    private Long gameDuration;

    @Transient
    private Long gameStartTimestamp;

    private String platformId;

    private Long gameCreation;

    private Long mapId;

    private String gameMode;

    @OneToMany(mappedBy = "match")
    private Set<MatchParticipant> matchParticipantSet;

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", gameId=" + gameId +
                ", gameType='" + gameType + '\'' +
                ", queueId=" + queueId +
                ", gameDuration=" + gameDuration +
                ", gameStartTimestamp=" + gameStartTimestamp +
                ", platformId='" + platformId + '\'' +
                ", gameCreation=" + gameCreation +
                ", mapId=" + mapId +
                ", gameMode='" + gameMode + '\'' +
                ", matchParticipantSet=" + matchParticipantSet +
                '}';
    }
}
