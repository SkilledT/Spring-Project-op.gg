package leagueoflegendsproject.Schedulers;

import leagueoflegendsproject.v2.processors.ChampionProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChampionScheduler {

    private final ChampionProcessor championProcessor;

    @Scheduled(cron = "0 */2 * * * ?")
    private void scheduleFetchingChampions() {
        log.info("scheduleFetchingChampions started");

        championProcessor.fetchAndSaveChampions();

        log.info("scheduleFetchingChampions finished");
    }

}
