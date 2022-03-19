package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.ChampionPerk;
import leagueoflegendsproject.Models.Database.Keys.ChampionPerkKey;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionPerkRepository extends JpaRepository<ChampionPerk, ChampionPerkKey> {

    @Override
    <S extends ChampionPerk> List<S> findAll(Example<S> example);

    @Query(value = "select cp from ChampionPerk cp WHERE cp.type = ?2 AND cp.champion.name = ?1")
    List<ChampionPerk> findChampionPerkByChampionNameAndType(String championName, String type);
}
