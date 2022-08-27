package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Perk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerkRepository extends JpaRepository<Perk, Integer>, leagueoflegendsproject.Repositories.PerkRepositoryCustom {

    @Override
    <S extends Perk> S save(S s);

    @Override
    Optional<Perk> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    <S extends Perk> List<S> saveAll(Iterable<S> entities);
}
