package leagueoflegendsproject.Controllers;

import leagueoflegendsproject.Services.HttpServices.HttpItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    private final HttpItemService httpItemService;

    public ItemController(HttpItemService httpItemService) {
        this.httpItemService = httpItemService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshItems() throws IOException, InterruptedException {
        return ResponseEntity.ok(httpItemService.refreshItems());
    }
}
