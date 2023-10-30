package leagueoflegendsproject.v2.processors;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Champions.ChampionItem;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services.IntegrationChampionService;
import leagueoflegendsproject.v2.Models.ChampionSnapshot;
import leagueoflegendsproject.v2.Models.ChampionImage;
import leagueoflegendsproject.v2.Models.ChampionInfo;
import leagueoflegendsproject.v2.Models.ChampionStats;
import leagueoflegendsproject.v2.Services.ChampionImageService;
import leagueoflegendsproject.v2.Services.ChampionInfoService;
import leagueoflegendsproject.v2.Services.ChampionSnapshotService;
import leagueoflegendsproject.v2.Services.ChampionStatsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ChampionProcessor {

    private final ChampionSnapshotService championService;
    private final ChampionInfoService championInfoService;
    private final ChampionStatsService championStatsService;
    private final ChampionImageService championImageService;
    private final IntegrationChampionService
            integrationChampionService;

    public void fetchAndSaveChampions() {
        log.info("=============== START FETCHING CHAMPIONS ===============");
        List<ChampionItem> championItems = integrationChampionService.getChampions().getResponse();
        for (var championItem : championItems) {
            if (championService.findByNameAndVersion(championItem.getName(), championItem.getVersion()).isEmpty()) {
                var champion = createChampion(championItem);
                createChampionInfo(championItem, champion);
                createChampionImage(championItem, champion);
                createChampionStats(championItem, champion);
            } else {
                // TODO: ADD AUDIT AND UPDATE ENTITY INSTEAD OF SKIPPING
                log.error("Champion with name {} and version {} already exist and will not be proceed",
                        championItem.getName(),
                        championItem.getVersion());
            }
        }
        log.info("=============== END FETCHING CHAMPIONS ===============");
    }

    private ChampionSnapshot createChampion(ChampionItem championItem) {
        return championService.createAndSaveChampion(
                championItem.getId(),
                championItem.getKey(),
                championItem.getVersion(),
                championItem.getName(),
                championItem.getTitle(),
                championItem.getBlurb(),
                championItem.getPartype()
        );
    }

    private ChampionInfo createChampionInfo(ChampionItem championItem, ChampionSnapshot championSnapshot) {
        var infoItem = championItem.getInfo();
        return championInfoService.createAndSave(
                infoItem.getAttack(),
                infoItem.getDefense(),
                infoItem.getMagic(),
                infoItem.getDifficulty(),
                championSnapshot
        );
    }

    private ChampionImage createChampionImage(ChampionItem championItem, ChampionSnapshot championSnapshot) {
        var imageItem = championItem.getImage();
        return championImageService.createAndSave(
                imageItem.getFull(),
                imageItem.getSprite(),
                imageItem.getGroup(),
                imageItem.getX(),
                imageItem.getY(),
                imageItem.getW(),
                imageItem.getH(),
                championSnapshot
        );
    }

    private ChampionStats createChampionStats(ChampionItem championItem, ChampionSnapshot championSnapshot) {
        var statsItem = championItem.getStats();
        return championStatsService.createAndSave(
                statsItem.getHp(),
                statsItem.getHpperlevel(),
                statsItem.getMp(),
                statsItem.getMpperlevel(),
                statsItem.getMovespeed(),
                statsItem.getArmor(),
                statsItem.getArmorperlevel(),
                statsItem.getSpellblock(),
                statsItem.getSpellblockperlevel(),
                statsItem.getAttackrange(),
                statsItem.getHpregen(),
                statsItem.getHpregenperlevel(),
                statsItem.getMpregen(),
                statsItem.getMpregenperlevel(),
                statsItem.getCrit(),
                statsItem.getCritperlevel(),
                statsItem.getAttackdamage(),
                statsItem.getAttackdamageperlevel(),
                statsItem.getAttackspeedperlevel(),
                statsItem.getAttackspeed(),
                championSnapshot
        );
    }
}
