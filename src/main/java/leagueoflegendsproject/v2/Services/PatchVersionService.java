package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.PatchVersion;
import leagueoflegendsproject.v2.Repositories.PatchVersionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PatchVersionService {

    private final PatchVersionRepository patchVersionRepository;

    public Optional<PatchVersion> findByVersion(String patchVersion) {
        return patchVersionRepository.findByVersion(patchVersion);
    }

    public PatchVersion savePatchVersion(String version, Timestamp timestamp) {
        var existingPatchVersion = findByVersion(version);
        if (existingPatchVersion.isPresent()) {
            if (!existingPatchVersion.get().getTimestamp().equals(timestamp)) {
                log.info("Patch Version with version {} already exists, timestamp has been changed from {} to {}",
                        version, existingPatchVersion.get().getTimestamp().getNanos(), timestamp.getNanos());
                existingPatchVersion.get()
                        .setTimestamp(timestamp);
                return patchVersionRepository.save(existingPatchVersion.get());
            }
            log.info("Patch Version with version {} already exists", version);
            return existingPatchVersion.get();
        }
        var newPatchVersion = new PatchVersion()
                .setVersion(version)
                .setTimestamp(timestamp);
        return patchVersionRepository.save(newPatchVersion);
    }


    public Optional<PatchVersion> findNewestPatchVersion(Timestamp timestamp) {
        var patchVersions = patchVersionRepository.findBeforeTimestampOrderByTimestampDesc(timestamp);
        if (patchVersions.isEmpty()) {
            return Optional.empty();
        }
        return patchVersions.stream().findFirst();
    }

    public Optional<PatchVersion> findNewestPatchVersion(long timestampInMillis) {
        Timestamp timestamp = new Timestamp(timestampInMillis);
        return findNewestPatchVersion(timestamp);
    }

    public List<PatchVersion> findAll() {
        return patchVersionRepository.findAll();
    }
}
