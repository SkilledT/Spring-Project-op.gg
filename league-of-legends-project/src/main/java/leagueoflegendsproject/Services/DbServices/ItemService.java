package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Repositories.ItemRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(leagueoflegendsproject.Models.LoLApi.Items.Item item){
        if (!itemRepository.existsById(item.getId())){
            return itemRepository.save(new Item(item));
        }
        var dbItem = itemRepository.findById(item.getId())
                .orElse(new Item(item));
        return itemRepository.save(updateItem(dbItem, item));
    }

    public Item updateItem(Item dbItem, leagueoflegendsproject.Models.LoLApi.Items.Item item){
        return itemRepository.save(dbItem.toUpdate(item));
    }
}
