package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Repositories.ItemRepository;
import leagueoflegendsproject.v2.Models.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item createItem(Integer externalId, String description, String plainText, Integer totalCost, Integer sell,
                           Integer baseCost, String name, String version) {
        return new Item()
                .setExternalId(externalId)
                .setDescription(description)
                .setPlainText(plainText)
                .setTotalCost(totalCost)
                .setSell(sell)
                .setBaseCost(baseCost)
                .setName(name)
                .setVersion(version);
    }

    public Item saveItem(Integer externalId, String description, String plainText, Integer totalCost, Integer sell,
                         Integer baseCost, String name, String version) {
        return itemRepository.save(createItem(externalId, description, plainText, totalCost, sell, baseCost, name, version));
    }

    public Optional<Item> getByExternalId(Integer externalId) {
        return itemRepository.findByExternalId(externalId);
    }

    public List<Item> getByExternalIdsAndVersion(List<Integer> externalIds, String version) {
        return itemRepository.findByExternalIdsAndVersion(externalIds, version);
    }

    public Optional<Item> findByExternalIdAndVersion(Integer externalId, String version) {
        return itemRepository.findByExternalIdAndVersion(externalId, version);
    }

}
