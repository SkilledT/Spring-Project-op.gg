package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MatchParticipantRepository extends JpaRepository<MatchParticipant, MatchParticipantKey> {
    List<MatchParticipant> findAll();

    @Override
    <S extends MatchParticipant> S save(S s);

    @Query("SELECT mp FROM MatchParticipant mp join fetch mp.champion c WHERE mp.summoner.summonerNickname = ?1 ORDER BY mp.match.gameCreation DESC")
    List<MatchParticipant> findBySummoner_SummonerNicknameOrderByMatch_GameCreationDesc(String nickname);

}
