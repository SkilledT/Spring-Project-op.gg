package leagueoflegendsproject.v2.Services;

import leagueoflegendsproject.v2.Models.Item;
import leagueoflegendsproject.v2.Models.ItemComponent;
import leagueoflegendsproject.v2.Repositories.ItemComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemComponentService {

    private final ItemComponentRepository itemComponentRepository;

    public ItemComponent createItemComponent(Item masterItem, Item component) {
        return ItemComponent.builder()
                .subComponent(component)
                .masterComponent(masterItem)
                .build();
    }

    public ItemComponent saveItemComponent(Item masterItem, Item component) {
        return itemComponentRepository.save(createItemComponent(masterItem, component));
    }
}
