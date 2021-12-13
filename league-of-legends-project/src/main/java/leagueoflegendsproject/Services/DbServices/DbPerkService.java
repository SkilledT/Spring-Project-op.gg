package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Repositories.PerkRepository;
import org.springframework.stereotype.Service;

@Service
public class DbPerkService {

    private final PerkRepository perkRepository;

    public DbPerkService(PerkRepository perkRepository) {
        this.perkRepository = perkRepository;
    }

    public Perk savePerk(Perk perk) throws Exception {
        if (!perkRepository.existsById(perk.getId()))
            return perkRepository.save(perk);
        return perkRepository.findById(perk.getId()).orElseThrow(() -> new Exception("There is not such a perk, but should be, why?"));
    }
}
