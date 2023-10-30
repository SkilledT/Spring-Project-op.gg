package leagueoflegendsproject.v2.Repositories;

import leagueoflegendsproject.v2.Models.RiftHerald;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiftHeraldRepository extends JpaRepository<RiftHerald, Long> {
}
