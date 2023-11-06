package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import com.google.gson.Gson;
import com.sun.jdi.request.InvalidRequestStateException;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.enums.DataDragonItemKeyEnum;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Items.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IntegrationItemService {

    private final RiotHttpClient riotHttpClient;

    public HttpResponseWrapper<List<Item>> getItems() {
        Gson gson = new Gson();
        HttpResponseWrapper<String> response = sendHttpRequest(RiotLinksProvider.ItemLinkProvider.RIOT_ITEMS_URL);

        Object responseObject = gson.fromJson(response.getResponse(), Object.class);
        /// keys: type, version, basic[], data[], groups[], tree[]
        Map<String, Object> wrappedObjects = (Map<String, Object>) responseObject;

        if (!wrappedObjects.keySet().contains(DataDragonItemKeyEnum.DATA.getValue())) {
            return new HttpResponseWrapper(false, null, response.getResponseMessage(), response.getStatusCode());
        }

        Map<String, Object> itemsById = getItemsByDataDragonEnum(wrappedObjects, DataDragonItemKeyEnum.DATA);
        String version = getItemsByDataDragonEnum(wrappedObjects, DataDragonItemKeyEnum.VERSION)
                .get(DataDragonItemKeyEnum.VERSION.getValue()).toString();

        List<Item> items = itemsById.entrySet().stream().map(entry -> {
            String json = gson.toJson(entry.getValue());
            Item cItem = gson.fromJson(json, Item.class);
            cItem.setId(Integer.parseInt(entry.getKey()));
            cItem.setVersion(version);
            return cItem;
        }).collect(Collectors.toList());
        return new HttpResponseWrapper(true, items, response.getResponse(), response.getStatusCode());
    }

    private Map<String, Object> getItemsByDataDragonEnum(Map<String, Object> wrappedObjects, DataDragonItemKeyEnum dataDragonItemKeyEnum) {
        return (Map<String, Object>) wrappedObjects.get(dataDragonItemKeyEnum.getValue());
    }

    private HttpResponseWrapper<String> sendHttpRequest(String url) {
        return riotHttpClient.get(url, String.class);
    }
}
