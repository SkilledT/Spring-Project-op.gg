package leagueoflegendsproject.v2.Processors;

import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.v2.Models.Champion;
import leagueoflegendsproject.v2.Models.ChampionImage;
import leagueoflegendsproject.v2.Models.ChampionInfo;
import leagueoflegendsproject.v2.Models.ChampionStats;
import leagueoflegendsproject.v2.Services.ChampionImageService;
import leagueoflegendsproject.v2.Services.ChampionInfoService;
import leagueoflegendsproject.v2.Services.ChampionService;
import leagueoflegendsproject.v2.Services.ChampionStatsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class ChampionProcessor {

    private final ChampionService championService;
    private final ChampionInfoService championInfoService;
    private final ChampionStatsService championStatsService;
    private final ChampionImageService championImageService;
    private final leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services.ChampionService
            integrationChampionService;

    public void fetchAndSaveChampions() {
        log.info("=============== START FETCHING CHAMPIONS ===============");
        List<ChampionItem> championItems = integrationChampionService.getChampions().getResponse();
        for (var championItem : championItems) {
            if (championService.findByExternalId(championItem.getId()).isEmpty()) {
                var champion = createChampion(championItem);
                createChampionInfo(championItem, champion);
                createChampionImage(championItem, champion);
                createChampionStats(championItem, champion);
            } else {
                // TODO: ADD AUDIT AND UPDATE ENTITY INSTEAD OF SKIPPING
                log.error("Champion with external ID {} already exist and will not be proceed", championItem.getId());
            }
        }
        log.info("=============== END FETCHING CHAMPIONS ===============");
    }

    private Champion createChampion(ChampionItem championItem) {
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

    private ChampionInfo createChampionInfo(ChampionItem championItem, Champion champion) {
        var infoItem = championItem.getInfo();
        return championInfoService.createAndSave(
                infoItem.getAttack(),
                infoItem.getDefense(),
                infoItem.getMagic(),
                infoItem.getDifficulty(),
                champion
        );
    }

    private ChampionImage createChampionImage(ChampionItem championItem, Champion champion) {
        var imageItem = championItem.getImage();
        return championImageService.createAndSave(
                imageItem.getFull(),
                imageItem.getSprite(),
                imageItem.getGroup(),
                imageItem.getX(),
                imageItem.getY(),
                imageItem.getW(),
                imageItem.getH(),
                champion
        );
    }

    private ChampionStats createChampionStats(ChampionItem championItem, Champion champion) {
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
                champion
        );
    }
}
