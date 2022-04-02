package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MatchParticipantRepository extends JpaRepository<MatchParticipant, MatchParticipantKey> {
    List<MatchParticipant> findAll();

    @Override
    <S extends MatchParticipant> S save(S s);

    List<MatchParticipant> findTop10BySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(String nickname);
}
