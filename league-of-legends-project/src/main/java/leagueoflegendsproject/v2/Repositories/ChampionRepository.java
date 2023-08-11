package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

    @Query("select e from leagueoflegendsproject.v2.Models.Champion e where e.externalId = :externalId")
    Optional<Champion> findByExternalId(@Param("externalId") String externalId);
}
