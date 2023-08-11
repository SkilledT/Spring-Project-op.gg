package leagueoflegendsproject.v2.Processors;

import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services.ItemService;
import leagueoflegendsproject.Models.LoLApi.Items.Item;
import leagueoflegendsproject.v2.Services.ItemComponentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Transactional
@Slf4j
public class ItemProcessor {

    private final ItemService integrationItemService;
    private final leagueoflegendsproject.v2.Services.ItemService dbItemService;
    private final ItemComponentService itemComponentService;

    public void fetchAndSaveItems() {
        log.info("=============== START FETCHING ITEMS ===============");
        var externalItems = integrationItemService.getItems().getResponse();

        for (var externalItem : externalItems) {
            if (dbItemService.getByExternalId(externalItem.getId()).isEmpty()) {
                saveExternalItem(externalItem);
            } else {
                // TODO: ADD AUDIT AND UPDATE ENTITY INSTEAD OF SKIPPING
                log.error("Item with external ID {} already exist and will not be proceed", externalItem.getId());
            }
        }

        for (var externalItem: externalItems) {
            var upgradeInto = externalItem.getInto().stream().map(Integer::parseInt)
                    .collect(Collectors.toList());
            var upgradeIntoItems = dbItemService.getByExternalIds(upgradeInto);
            var componentItem = dbItemService.getByExternalId(externalItem.getId());
            if (componentItem.isEmpty()) {
                log.error("Couldn't find item with external ID: {} so parental items won't be updated",
                        externalItem.getId());
                continue;
            }
            saveItemComponents(upgradeIntoItems, componentItem.get());
        }
        log.info("=============== END FETCHING ITEMS ===============");
    }

    private void saveItemComponents(List<leagueoflegendsproject.v2.Models.Item> masterItems,
                                    leagueoflegendsproject.v2.Models.Item componentItem) {
        for (var masterItem: masterItems) {
            itemComponentService.saveItemComponent(masterItem, componentItem);
        }
    }

    private void saveExternalItem(Item externalItem) {
        dbItemService.saveItem(
                externalItem.getId(),
                externalItem.getDescription(),
                externalItem.getPlaintext(),
                externalItem.getGold().getTotal(),
                externalItem.getGold().getSell(),
                externalItem.getGold().getBase(),
                externalItem.getName()
        );
    }

}
