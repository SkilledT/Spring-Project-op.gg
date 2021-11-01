package leagueoflegendsproject.Models.Database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "match")
    private Set<MatchTeam> matchTeamSet;

    public Match(){}

    public Match(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match){
        this.id = match.getMetadata().getMatchId();
        this.gameId = match.getInfo().getGameId();
        this.gameType = match.getInfo().getGameType();
        this.queueId = (long) match.getInfo().getQueueId();
        this.gameDuration = (long) match.getInfo().getGameDuration();
        this.platformId = match.getInfo().getPlatformId();
        this.gameCreation = (long) match.getInfo().getGameCreation();
        this.mapId = (long) match.getInfo().getMapId();
        this.gameMode = match.getInfo().getGameMode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id.equals(match.id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public void setGameDuration(Long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public void setGameStartTimestamp(Long gameStartTimestamp) {
        this.gameStartTimestamp = gameStartTimestamp;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setGameCreation(Long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public void setMatchParticipantSet(Set<MatchParticipant> matchParticipantSet) {
        this.matchParticipantSet = matchParticipantSet;
    }

    public Set<MatchParticipant> getMatchParticipantSet() {
        return matchParticipantSet;
    }

    public void setMatchTeamSet(Set<MatchTeam> matchTeamSet) {
        this.matchTeamSet = matchTeamSet;
    }

    public Set<MatchTeam> getMatchTeamSet() {
        return matchTeamSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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
