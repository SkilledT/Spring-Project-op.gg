package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion.ChampionTag;
import leagueoflegendsproject.Models.Database.Champion.ChampionTagKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionTagRepository extends JpaRepository<ChampionTag, ChampionTagKey> {
    @Override
    <S extends ChampionTag> S save(S s);

    @Override
    Optional<ChampionTag> findById(ChampionTagKey championTagKey);
}
