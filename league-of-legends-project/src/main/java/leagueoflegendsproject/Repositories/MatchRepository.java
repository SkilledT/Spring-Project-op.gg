package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, String> {

    @Override
    <S extends Match> S save(S s);

    @Query("SELECT m FROM Match m JOIN FETCH m.matchParticipantSet mp WHERE mp.summoner.summonerNickname = ?1 AND mp.individualPosition <> 'Invalid' ORDER BY m.gameCreation DESC")
    List<Match> findBySummoner_SummonerNicknameAndFilteredOrderByMatch_GameCreationDesc(String nickname);

}
