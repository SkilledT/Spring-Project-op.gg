package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Override
    <S extends Tag> S save(S s);

    Optional<Tag> findByName(String name);
}