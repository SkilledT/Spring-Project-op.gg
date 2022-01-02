package leagueoflegendsproject.Repositories;

import leagueoflegendsproject.Models.Database.ItemCookBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCookBookRepository extends JpaRepository<ItemCookBook, Integer> {

    @Override
    void deleteAll();

    @Override
    <S extends ItemCookBook> List<S> saveAll(Iterable<S> entities);

    @Override
    <S extends ItemCookBook> S save(S s);
}
