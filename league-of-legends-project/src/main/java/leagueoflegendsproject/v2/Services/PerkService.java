package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Perk;
import leagueoflegendsproject.v2.Repositories.PerkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerkService {

    private final PerkRepository perkRepository;

    public Perk createPerk(String key, String name, String icon, String shortDesc, String longDesc, Integer slotNumber, Integer treeNumber, String version) {
        return new Perk()
                .setKey(key)
                .setName(name)
                .setIcon(icon)
                .setShortDesc(shortDesc)
                .setLongDesc(longDesc)
                .setSlotNumber(slotNumber)
                .setTreeNumber(treeNumber)
                .setVersion(version);
    }

    public Perk savePerk(String key, String name, String icon, String shortDesc, String longDesc, Integer slotNumber, Integer treeNumber, String version) {
        return perkRepository.save(createPerk(key, name, icon, shortDesc, longDesc, slotNumber, treeNumber, version));
    }

    public List<Perk> findByExternalIdsAndVersion(List<Integer> ids, String version) {
        return  perkRepository.findByExternalIdsAndVersion(ids, version);
    }

}
