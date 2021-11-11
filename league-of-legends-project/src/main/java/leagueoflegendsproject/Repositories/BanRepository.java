package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Ban;
import leagueoflegendsproject.Models.Database.Keys.BanKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, BanKey> {
    @Override
    <S extends Ban> S save(S s);
}
