package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.Perk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerkRepository extends JpaRepository<Perk, Long> {

    @Query("select e from Perk e where e.version = :version and e.externalId in :externalIds")
    List<Perk> findByExternalIdsAndVersion(List<Integer> externalIds, String version);
}
