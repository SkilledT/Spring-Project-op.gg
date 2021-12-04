package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Override
    <S extends Image> S save(S s);
}
