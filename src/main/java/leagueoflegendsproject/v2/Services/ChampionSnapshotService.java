package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import leagueoflegendsproject.v2.Repositories.ChampionSnapshotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChampionSnapshotService {

    private final ChampionSnapshotRepository championSnapshotRepository;

    public ChampionSnapshot createChampion(String externalId,
                                           String key,
                                           String version,
                                           String name,
                                           String title,
                                           String blurb,
                                           String partype) {
        return new ChampionSnapshot()
                .setExternalId(externalId)
                .setKey(key)
                .setVersion(version)
                .setName(name)
                .setTitle(title)
                .setBlurb(blurb)
                .setPartype(partype);
    }

    public ChampionSnapshot createAndSaveChampion(String externalId,
                                                  String key,
                                                  String version,
                                                  String name,
                                                  String title,
                                                  String blurb,
                                                  String partype) {
        return championSnapshotRepository.save(createChampion(externalId, key, version, name, title, blurb, partype));
    }

    public Optional<ChampionSnapshot> findByExternalId(String externalId) {
        return championSnapshotRepository.findByExternalId(externalId);
    }

    public Optional<ChampionSnapshot> findByNameAndVersion(String name, String version) {
        return championSnapshotRepository.findByNameAndVersion(name, version);
    }

    public List<ChampionSnapshot> findByExternalIdAndVersion(List<String> externalIds, String version) {
        return championSnapshotRepository.findByExternalIdAndVersion(externalIds, version);
    }
}
