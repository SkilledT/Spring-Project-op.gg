package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.MatchParticipantPerkKey;
import leagueoflegendsproject.Models.Database.MatchParticipantPerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipantPerkRepository extends JpaRepository<MatchParticipantPerk, MatchParticipantPerkKey> {

    @Override
    <S extends MatchParticipantPerk> S save(S s);
}
