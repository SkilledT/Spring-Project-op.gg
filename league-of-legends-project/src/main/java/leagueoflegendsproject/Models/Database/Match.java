package leagueoflegendsproject.Models.Database;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @Column(name = "match_id")
    private String matchId;
    @Column(name = "game_id")
    private Integer gameId;
    @Column(name = "game_type")
    private String gameType;
    @Column(name = "queue_id")
    private Integer queueId;
    @Column(name = "game_duration")
    private Integer gameDuration;
    @Column(name = "game_start_timestamp")
    private Integer gameStartTimestamp;
    @Column(name = "platform_id")
    private String platformId;
    @Column(name = "game_creation")
    private Long gameCreation;
    @Column(name = "map_id")
    private Integer mapId;
    @Column(name = "game_mode")
    private String gameMode;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MatchTeam> matchTeamSet = new HashSet<>();

    public void addMatchParticipantChild(MatchParticipant matchParticipant) {
        this.matchParticipantSet.add(matchParticipant);
        matchParticipant.setMatch(this);
    }

    public void addMatchTeamChild(MatchTeam matchTeam) {
        this.matchTeamSet.add(matchTeam);
        matchTeam.setMatch(this);
    }

    public Match(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match){
        this.matchId = match.getMetadata().getMatchId();
        this.gameId = (int) match.getInfo().getGameId();
        this.gameType = match.getInfo().getGameType();
        this.queueId = match.getInfo().getQueueId();
        this.gameDuration = 0;
        this.platformId = match.getInfo().getPlatformId();
        this.gameCreation = match.getInfo().getGameCreation();
        this.mapId = match.getInfo().getMapId();
        this.gameMode = match.getInfo().getGameMode();
        this.gameStartTimestamp = (int) match.getInfo().getGameStartTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(matchId, match.matchId) &&
                Objects.equals(gameId, match.gameId) &&
                Objects.equals(gameType, match.gameType) &&
                Objects.equals(queueId, match.queueId) &&
                Objects.equals(gameDuration, match.gameDuration) &&
                Objects.equals(gameStartTimestamp, match.gameStartTimestamp) &&
                Objects.equals(platformId, match.platformId) &&
                Objects.equals(gameCreation, match.gameCreation) &&
                Objects.equals(mapId, match.mapId) &&
                Objects.equals(gameMode, match.gameMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, gameId, gameType, queueId, gameDuration, gameStartTimestamp, platformId, gameCreation, mapId, gameMode);
    }
}
