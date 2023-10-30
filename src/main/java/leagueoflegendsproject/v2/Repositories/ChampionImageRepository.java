package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.ChampionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionImageRepository extends JpaRepository<ChampionImage, Long> {
}
