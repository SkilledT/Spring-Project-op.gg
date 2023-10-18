package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.v2.Processors.PatchVersionProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PatchVersionScheduler {

    private final PatchVersionProcessor patchVersionProcessor;

    @Scheduled(cron = "0 */2 * * * ?")
    private void scheduleScrappingPatchVersion() {
        log.info("scheduleScrappingPatchVersion started");

        patchVersionProcessor.processPatchVersions();

        log.info("scheduleScrappingPatchVersion finished");
    }
}
