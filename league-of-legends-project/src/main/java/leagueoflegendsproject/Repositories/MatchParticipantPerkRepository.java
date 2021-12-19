package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.MatchParticipantPerkKey;
import leagueoflegendsproject.Models.Database.MatchParticipantPerk;
import leagueoflegendsproject.Models.Database.Perk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchParticipantPerkRepository extends JpaRepository<MatchParticipantPerk, MatchParticipantPerkKey> {

    @Override
    <S extends MatchParticipantPerk> S save(S s);

}
