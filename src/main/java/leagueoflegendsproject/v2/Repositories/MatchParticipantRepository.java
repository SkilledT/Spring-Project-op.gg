package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.MatchParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipantRepository extends JpaRepository<MatchParticipant, Long> {
}
