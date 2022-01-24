package leagueoflegendsproject.Repositories.Implementations;

import leagueoflegendsproject.Models.Database.MatchParticipant;
import leagueoflegendsproject.Models.Database.TemporaryTables.MatchParticipantShortModel;
import leagueoflegendsproject.Repositories.Interfaces.CustomMatchParticipantRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomMatchParticipantRepositoryImpl implements CustomMatchParticipantRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MatchParticipantShortModel> findBySummonerNickname(String nickname) {
        TypedQuery<MatchParticipantShortModel> query = entityManager
                .createQuery("select new leagueoflegendsproject.Models.Database.TemporaryTables.MatchParticipantShortModel(mp.team, mp.match, mp.summoner,mp. champion, mp.win, mp.Kills, mp.deaths, mp.assists, mp.totalMinionsKilled, mp.champLvl, mp.championName, mp.individualPosition, mp.summoner1Id, mp.summoner2Id, mp.visionWardsBoughtInGame) from MatchParticipant as mp where mp.summoner.summonerNickname = :nickname",
                        MatchParticipantShortModel.class);
        query.setParameter("nickname", nickname);
        return query.getResultList();
    }
}
