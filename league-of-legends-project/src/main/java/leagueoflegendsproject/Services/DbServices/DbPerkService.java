package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.ChampionPerkDTO;
import leagueoflegendsproject.DTOs.PerkDto;
import leagueoflegendsproject.Models.Database.ChampionPerk;
import leagueoflegendsproject.Models.Database.Perk;
import leagueoflegendsproject.Repositories.ChampionPerkRepository;
import leagueoflegendsproject.Repositories.PerkRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbPerkService {

    private final PerkRepository perkRepository;
    private final ChampionPerkRepository championPerkRepository;

    public DbPerkService(PerkRepository perkRepository,
                         ChampionPerkRepository championPerkRepository) {
        this.perkRepository = perkRepository;
        this.championPerkRepository = championPerkRepository;
    }

    public Perk savePerk(Perk perk) throws Exception {
        if (!perkRepository.existsById(perk.getId()))
            return perkRepository.save(perk);
        return perkRepository.findById(perk.getId())
                .orElseThrow(() -> new Exception("There is not such a perk, but should be, why?"));
    }

    public List<ChampionPerkDTO> getPerkByChampionNameAndTreeType(String championName, String treeType){
        return championPerkRepository.findChampionPerkByChampionNameAndType(championName, treeType).stream()
                .map(ChampionPerkDTO::new)
                .collect(Collectors.toList());
    }

    public enum TreeType {
        MAIN, SUB
    }
}
