package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Champion.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {

    @Override
    <S extends Info> S save(S s);
}
