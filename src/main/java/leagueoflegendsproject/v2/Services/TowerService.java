package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.*;
import leagueoflegendsproject.v2.Repositories.TowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TowerService {

    private final TowerRepository towerRepository;

    public Tower createTower(int kills, boolean first) {
        return Tower.builder()
                .first(first)
                .kills(kills)
                .build();
    }

    public Tower saveTower(int kills, boolean first) {
        var tower = createTower(kills, first);
        return towerRepository.save(tower);
    }

}
