package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Keys.ParticipantItemsKey;
import leagueoflegendsproject.Models.Database.ParticipantItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantItemsRepository extends JpaRepository<ParticipantItems, Integer> {
    @Override
    <S extends ParticipantItems> S save(S s);
}
