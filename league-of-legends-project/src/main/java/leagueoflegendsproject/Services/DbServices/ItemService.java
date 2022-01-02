package leagueoflegendsproject.Services.DbServices;

import leagueoflegendsproject.DTOs.ItemDto;
import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Models.Database.ItemCookBook;
import leagueoflegendsproject.Repositories.Interfaces.ItemRepositoryCustom;
import leagueoflegendsproject.Repositories.ItemCookBookRepository;
import leagueoflegendsproject.Repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemCookBookRepository itemCookBookRepository;
    private final ItemRepositoryCustom itemRepositoryCustom;

    public ItemService(ItemRepository itemRepository,
                       ItemCookBookRepository itemCookBookRepository,
                       ItemRepositoryCustom itemRepositoryCustom) {
        this.itemRepository = itemRepository;
        this.itemCookBookRepository = itemCookBookRepository;
        this.itemRepositoryCustom = itemRepositoryCustom;
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

    public void addParentalItems(leagueoflegendsproject.Models.LoLApi.Items.Item item){
        if (item.getInto() == null)
            return;

        Item itemComponent = itemRepository.findById(item.getId())
                .orElseThrow(() -> new IllegalStateException("There's no such an item in DB"));
        for (String id : item.getInto()){
            ItemCookBook itemCookBook = new ItemCookBook();
            itemCookBook.setItemComponent(itemComponent);
            Item masterItem = itemRepository.findById(Integer.valueOf(id))
                    .orElseThrow(() -> new IllegalStateException("There's no such an item in DB"));
            itemCookBook.setItemMaster(masterItem);
            itemCookBookRepository.save(itemCookBook);
        }
    }

    public List<Item> getMostPopularItemsForChampion(String championName){
        var res = itemRepositoryCustom.getMostPopularItemsForChampion(championName);
        return res;
    }

    public void deleteAllItemCookBook(){
        itemCookBookRepository.deleteAll();
    }
}
