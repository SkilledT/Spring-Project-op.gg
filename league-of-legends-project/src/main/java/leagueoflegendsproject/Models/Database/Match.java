package leagueoflegendsproject.Models.Database;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Match")
public class Match {

    @Id
    @Column(name = "match_id")
    private String matchId;

    private Integer gameId;

    private String gameType;

    private Integer queueId;

    private Integer gameDuration;

    @Transient
    private Integer gameStartTimestamp;

    private String platformId;

    private Integer gameCreation;

    private Integer mapId;

    private String gameMode;

    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private Set<MatchParticipant> matchParticipantSet = new HashSet<>();

    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private Set<MatchTeam> matchTeamSet = new HashSet<>();

    public Match(){}

    public Match(String matchId, Integer gameId, String gameType, Integer queueId, Integer gameDuration, Integer gameStartTimestamp, String platformId, Integer gameCreation, Integer mapId, String gameMode, Set<MatchParticipant> matchParticipantSet, Set<MatchTeam> matchTeamSet) {
        this.matchId = matchId;
        this.gameId = gameId;
        this.gameType = gameType;
        this.queueId = queueId;
        this.gameDuration = gameDuration;
        this.gameStartTimestamp = gameStartTimestamp;
        this.platformId = platformId;
        this.gameCreation = gameCreation;
        this.mapId = mapId;
        this.gameMode = gameMode;
        this.matchParticipantSet = matchParticipantSet;
        this.matchTeamSet = matchTeamSet;
    }

    public Match(leagueoflegendsproject.Models.LoLApi.Matches.matchId.Match match){
        this.matchId = match.getMetadata().getMatchId();
        this.gameId = (int) match.getInfo().getGameId();
        this.gameType = match.getInfo().getGameType();
        this.queueId = match.getInfo().getQueueId();
        this.gameDuration = 0;
        this.platformId = match.getInfo().getPlatformId();
        this.gameCreation = 0;
        this.mapId = match.getInfo().getMapId();
        this.gameMode = match.getInfo().getGameMode();
        this.gameStartTimestamp = 0;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String id) {
        this.matchId = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    public Integer getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(Integer gameDuration) {
        this.gameDuration = gameDuration;
    }

    public Integer getGameStartTimestamp() {
        return gameStartTimestamp;
    }

    public void setGameStartTimestamp(Integer gameStartTimestamp) {
        this.gameStartTimestamp = gameStartTimestamp;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Integer getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(Integer gameCreation) {
        this.gameCreation = gameCreation;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Set<MatchParticipant> getMatchParticipantSet() {
        return matchParticipantSet;
    }

    public void setMatchParticipantSet(Set<MatchParticipant> matchParticipantSet) {
        this.matchParticipantSet = matchParticipantSet;
    }

    public Set<MatchTeam> getMatchTeamSet() {
        return matchTeamSet;
    }

    public void setMatchTeamSet(Set<MatchTeam> matchTeamSet) {
        this.matchTeamSet = matchTeamSet;
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
