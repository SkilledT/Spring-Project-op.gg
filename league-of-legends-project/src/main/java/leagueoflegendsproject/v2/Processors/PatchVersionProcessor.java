package leagueoflegendsproject.v2.Processors;

import leagueoflegendsproject.v2.Scrappers.PatchVersionScrapper;
import leagueoflegendsproject.v2.Services.PatchVersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatchVersionProcessor {

    private final PatchVersionScrapper patchVersionScrapper;
    private final PatchVersionService patchVersionService;

    public void processPatchVersions() {
        var patchVersions = patchVersionScrapper.scrapPatchVersions();
        patchVersions.forEach(scrappedVersion -> {
            patchVersionService.savePatchVersion(scrappedVersion.getVersion(),
                    Timestamp.from(scrappedVersion.getDate()));
        });
    }
}
