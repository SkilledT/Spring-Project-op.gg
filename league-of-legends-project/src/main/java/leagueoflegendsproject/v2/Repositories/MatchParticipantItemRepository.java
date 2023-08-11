package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.MatchParticipantItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipantItemRepository extends JpaRepository<MatchParticipantItem, Long> {
}
