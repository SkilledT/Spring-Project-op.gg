package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Match;
import leagueoflegendsproject.v2.Repositories.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public Match createMatch(String matchId,
                             Integer gameId,
                             String gameType,
                             Integer queueId,
                             Integer gameDuration,
                             Integer gameStartTimestamp,
                             String platformId,
                             Long gameCreation,
                             Integer mapId,
                             String gameMode,
                             String apiVersion
    ) {
        return new Match()
                .setExternalMatchId(matchId)
                .setGameId(gameId)
                .setGameType(gameType)
                .setQueueId(queueId)
                .setGameDuration(gameDuration)
                .setGameStartTimestamp(gameStartTimestamp)
                .setPlatformId(platformId)
                .setGameCreation(gameCreation)
                .setMapId(mapId)
                .setGameMode(gameMode)
                .setApiVersion(apiVersion);
    }

    public Match createMatchAndSave(String matchId,
                                    Integer gameId,
                                    String gameType,
                                    Integer queueId,
                                    Integer gameDuration,
                                    Integer gameStartTimestamp,
                                    String platformId,
                                    Long gameCreation,
                                    Integer mapId,
                                    String gameMode,
                                    String apiVersion
    ) {
        return matchRepository.save(createMatch(
                matchId, gameId, gameType, queueId, gameDuration, gameStartTimestamp,
                platformId, gameCreation, mapId, gameMode, apiVersion
        ));
    }

    public Optional<Match> findByMatchId(String matchId) {
        return matchRepository.findByMatchId(matchId);
    }
}
