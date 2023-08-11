package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Champion;
import leagueoflegendsproject.v2.Repositories.ChampionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChampionService {

    private final ChampionRepository championRepository;

    public Champion createChampion(String externalId,
                                   String key,
                                   String version,
                                   String name,
                                   String title,
                                   String blurb,
                                   String partype) {
        return Champion.builder()
                .externalId(externalId)
                .key(key)
                .version(version)
                .name(name)
                .title(title)
                .blurb(blurb)
                .partype(partype)
                .build();
    }

    public Champion createAndSaveChampion(String externalId,
                                          String key,
                                          String version,
                                          String name,
                                          String title,
                                          String blurb,
                                          String partype) {
        return championRepository.save(createChampion(externalId, key, version, name, title, blurb, partype));
    }

    public Optional<Champion> findByExternalId(String externalId) {
        return championRepository.findByExternalId(externalId);
    }
}
