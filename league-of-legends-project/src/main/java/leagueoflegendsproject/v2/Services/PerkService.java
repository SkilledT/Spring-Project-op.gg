package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Perk;
import leagueoflegendsproject.v2.Repositories.PerkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PerkService {

    private final PerkRepository perkRepository;

    public Perk createPerk(String key, String name, String icon, String shortDesc, String longDesc, Integer slotNumber, Integer treeNumber) {
        return Perk.builder()
                .key(key)
                .name(name)
                .icon(icon)
                .shortDesc(shortDesc)
                .longDesc(longDesc)
                .slotNumber(slotNumber)
                .treeNumber(treeNumber)
                .build();
    }

    public Perk savePerk(String key, String name, String icon, String shortDesc, String longDesc, Integer slotNumber, Integer treeNumber) {
        return perkRepository.save(createPerk(key, name, icon, shortDesc, longDesc, slotNumber, treeNumber));
    }

}
