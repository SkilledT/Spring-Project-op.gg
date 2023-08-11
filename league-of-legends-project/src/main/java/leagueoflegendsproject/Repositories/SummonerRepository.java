package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.Summoner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummonerRepository extends JpaRepository<Summoner, String> {

    Optional<Summoner> findById(String s);

    @Override
    <S extends Summoner> S save(S s);

    Optional<Summoner> findSummonerBySummonerNickname(String nickname);

    List<Summoner> findSummonerByTier(String tier);

    @Query(value = "select s from Summoner s join fetch s.matchParticipantSet m",
    countQuery = "select count(s) from Summoner s join s.matchParticipantSet m")
    Page<Summoner> findAllPageSummoners(Pageable pageable);
}
