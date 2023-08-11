package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.jdi.request.InvalidRequestStateException;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.enums.DataDragonChampionKeyEnum;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.enums.DataDragonItemKeyEnum;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.Models.LoLApi.Items.Item;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ChampionService {
    private final RiotHttpClient httpClient;

    public HttpResponseWrapper<List<ChampionItem>> getChampions() {
        Gson gson = new Gson();
        HttpResponseWrapper<String> response = sendHttpRequest(RiotLinksProvider.RIOT_CHAMPION_URL);

        Object o = gson.fromJson(response.getResponse(), Object.class);
        /// keys: type, version, format, data[]
        Map<String, Object> wrappedObjects = (Map<String, Object>) o;

        if (!wrappedObjects.keySet().contains(DataDragonChampionKeyEnum.DATA.getValue())) {
            return new HttpResponseWrapper(false, null, response.getResponseMessage());
        }

        Map<String, Object> championsByName = getChampionsByDataDragonEnum(wrappedObjects, DataDragonChampionKeyEnum.DATA);

        List<ChampionItem> items = championsByName.entrySet().stream().map(entry -> {
            var json = gson.toJson(entry.getValue());
            var cItem = gson.fromJson(json, ChampionItem.class);
            return cItem;
        }).collect(Collectors.toList());
        return new HttpResponseWrapper(true, items, response.getResponse());
    }

    private Map<String, Object> getChampionsByDataDragonEnum(Map<String, Object> wrappedObjects, DataDragonChampionKeyEnum dataDragonItemKeyEnum) {
        return (Map<String, Object>) wrappedObjects.get(dataDragonItemKeyEnum.getValue());
    }


    private HttpResponseWrapper<String> sendHttpRequest(String url) {
        try {
            return httpClient.get(url, String.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new InvalidRequestStateException("Unable to send request to RIOT's API");
        }
    }
}
