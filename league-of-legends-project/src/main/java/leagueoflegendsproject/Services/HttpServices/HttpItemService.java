package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.ItemDto;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Models.LoLApi.Items.Item;
import leagueoflegendsproject.Services.DbServices.ItemService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HttpItemService {

    private final ItemService itemService;
    private final RiotHttpClient riotHttpClient;

    public HttpItemService(ItemService itemService, RiotHttpClient riotHttpClient) {
        this.itemService = itemService;
        this.riotHttpClient = riotHttpClient;
    }

    public List<ItemDto> refreshItems() throws IOException, InterruptedException {
        var itemsWrapper = riotHttpClient.getItems();
        if (itemsWrapper.isSuccess()){
            List<Item> items = itemsWrapper.getResponse();
            var dbItems = items
                    .stream()
                    .map(itemService::addItem)
                    .collect(Collectors.toList());
            itemService.deleteAllItemCookBook();
            items.forEach(itemService::addParentalItems);
            return dbItems.stream().map(ItemDto::new)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
