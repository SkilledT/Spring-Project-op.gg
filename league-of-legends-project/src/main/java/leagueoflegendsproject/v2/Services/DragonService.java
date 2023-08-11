package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Dragon;
import leagueoflegendsproject.v2.Repositories.DragonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DragonService {

    private final DragonRepository dragonRepository;

    public Dragon createDragon(int kills, boolean first) {
        return Dragon.builder()
                .first(first)
                .kills(kills)
                .build();
    }

    public Dragon saveDragon(int kills, boolean first) {
        var dragon = createDragon(kills, first);
        return dragonRepository.save(dragon);
    }

}
