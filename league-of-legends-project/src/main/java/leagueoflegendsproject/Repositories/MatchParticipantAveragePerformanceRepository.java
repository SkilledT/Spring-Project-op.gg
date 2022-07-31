package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Helpers.TestUtils.Constants;
import leagueoflegendsproject.Models.Database.AgregateEntities.MatchParticipantAveragePerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchParticipantAveragePerformanceRepository extends JpaRepository<MatchParticipantAveragePerformance, Long> {

    @Override
    <S extends MatchParticipantAveragePerformance> List<S> saveAll(Iterable<S> entities);


    @Query("select m from MatchParticipantAveragePerformance m where m.tier = ?1 and m.individualPosition = ?2")
    Optional<MatchParticipantAveragePerformance> findByTierAndIndividualPosition(String tier, Constants.MatchParticipantConstants.IndividualPosition individualPosition);
}
