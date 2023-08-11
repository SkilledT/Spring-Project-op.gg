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
                           Integer baseCost, String name) {
        return Item.builder()
                .externalId(externalId)
                .description(description)
                .plainText(plainText)
                .totalCost(totalCost)
                .sell(sell)
                .baseCost(baseCost)
                .name(name)
                .build();
    }

    public Item saveItem(Integer externalId, String description, String plainText, Integer totalCost, Integer sell,
                         Integer baseCost, String name) {
        return itemRepository.save(createItem(externalId, description, plainText, totalCost, sell, baseCost, name));
    }

    public Optional<Item> getByExternalId(Integer externalId) {
        return itemRepository.findByExternalId(externalId);
    }

    public List<Item> getByExternalIds(List<Integer> externalIds) {
        return itemRepository.findByExternalIds(externalIds);
    }

}
