package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionSnapshotRepository extends JpaRepository<ChampionSnapshot, Long> {

    @Query("select e from ChampionSnapshot e where e.externalId = :externalId")
    Optional<ChampionSnapshot> findByExternalId(@Param("externalId") String externalId);

    @Query("select e from ChampionSnapshot e where e.name = :name and e.version = :version")
    Optional<ChampionSnapshot> findByNameAndVersion(@Param("name") String name, @Param("version") String version);

    @Query("select e from ChampionSnapshot e where e.externalId IN :externalIds and e.version = :version")
    List<ChampionSnapshot> findByExternalIdAndVersion(@Param("externalIds") List<String> externalIds,
                                                          @Param("version") String version);
}
