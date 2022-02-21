package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.ApplicationUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
    @Override
    Optional<ApplicationUser> findById(String s);
    @Override
    <S extends ApplicationUser> List<S> findAll(Example<S> example);
}
