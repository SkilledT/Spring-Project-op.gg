package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.PatchVersion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatchVersionRepository extends JpaRepository<PatchVersion, Long> {

    @Query("select e from PatchVersion e where e.version = :version")
    Optional<PatchVersion> findByVersion(@Param("version") String version);

    @Query("select e from PatchVersion e where e.timestamp < :timestamp order by e.timestamp desc")
    List<PatchVersion> findBeforeTimestampOrderByTimestampDesc(@Param("timestamp") Timestamp timestamp);
}
