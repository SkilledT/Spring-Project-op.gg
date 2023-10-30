package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.MatchParticipantPerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipantPerkRepository extends JpaRepository<MatchParticipantPerk, Long> {
}
