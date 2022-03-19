package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion.Champion;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Integer> {

    @Override
    Optional<Champion> findById(Integer integer);

    @Override
    <S extends Champion> S save(S s);

    @Override
    boolean existsById(Integer integer);

    @Override
    <S extends Champion> List<S> findAll(Example<S> example);

}
