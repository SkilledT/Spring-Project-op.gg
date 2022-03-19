package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.MatchParticipantKey;
import leagueoflegendsproject.Models.Database.MatchParticipant;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MatchParticipantRepository extends JpaRepository<MatchParticipant, MatchParticipantKey> {
    List<MatchParticipant> findAll();

    @Override
    <S extends MatchParticipant> S save(S s);

    @Query(value = "select top 10 * from Match_participant " +
            "inner join Summoner on Match_participant.Summoner_summoner_id = Summoner.summoner_id " +
            "inner join Match M on M.match_id = Match_participant.Match_match_id " +
            "where summoner_nickname = :nickname order by M.game_creation desc", nativeQuery = true)
    List<MatchParticipant> findBySummonerNickname(String nickname);
}
