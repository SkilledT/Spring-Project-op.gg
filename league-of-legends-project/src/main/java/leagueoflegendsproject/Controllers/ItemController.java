package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.DTOs.ItemDto;
import leagueoflegendsproject.Models.Database.Item;
import leagueoflegendsproject.Services.DbServices.ItemService;
import leagueoflegendsproject.Services.HttpServices.HttpItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    private final HttpItemService httpItemService;
    private final ItemService itemService;

    public ItemController(HttpItemService httpItemService,
                          ItemService itemService) {
        this.httpItemService = httpItemService;
        this.itemService = itemService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshItems() throws IOException, InterruptedException {
        return ResponseEntity.ok(httpItemService.refreshItems());
    }

    @GetMapping("/mostPopular/{championName}")
    public ResponseEntity<?> getMostPopularItemsForChampion(@PathVariable String championName) {
        return ResponseEntity.ok(itemService.getMostPopularItemsForChampion(championName));
    }
}
