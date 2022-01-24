package leagueoflegendsproject.Repositories.Interfaces;

import leagueoflegendsproject.Models.Database.TemporaryTables.MatchParticipantShortModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomMatchParticipantRepository {
    List<MatchParticipantShortModel> findBySummonerNickname(String nickname);
}
