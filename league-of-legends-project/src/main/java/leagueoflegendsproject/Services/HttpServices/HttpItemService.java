package leagueoflegendsproject.Services.HttpServices;

import leagueoflegendsproject.DTOs.ItemDto;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Services.DbServices.ItemService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
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
            var items = itemsWrapper.getResponse();
            var dbItems = items.stream().map(itemService::addItem).collect(Collectors.toList());
            return dbItems.stream().map(ItemDto::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
