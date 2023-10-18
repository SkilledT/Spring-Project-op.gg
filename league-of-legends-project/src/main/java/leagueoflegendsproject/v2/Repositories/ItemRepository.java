package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select e from Item e where e.externalId = :externalId")
    Optional<Item> findByExternalId(@Param("externalId") Integer externalId);

    @Query("select e from Item e where e.externalId = :externalId and e.version = :version")
    Optional<Item> findByExternalIdAndVersion(@Param("externalId") Integer externalId, @Param("version") String version);

    @Query("select e from Item e where e.externalId IN :externalIds and e.version = :version")
    List<Item> findByExternalIdsAndVersion(List<Integer> externalIds, String version);
}
