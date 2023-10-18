package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Dragon;
import leagueoflegendsproject.v2.Repositories.DragonRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
