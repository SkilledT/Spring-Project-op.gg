package leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.Services;

import com.google.gson.Gson;
import com.sun.jdi.request.InvalidRequestStateException;
import leagueoflegendsproject.Helpers.HttpResponseWrapper;
import leagueoflegendsproject.Helpers.RiotHttpClient;
import leagueoflegendsproject.Helpers.RiotLinksProvider;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.enums.DataDragonChampionKeyEnum;
import leagueoflegendsproject.Integrations.Riot.LeagueOfLegends.ApiModels.Champions.ChampionItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IntegrationChampionService {
    private final RiotHttpClient httpClient;

    public HttpResponseWrapper<List<ChampionItem>> getChampions(String patchVersion) {
        Gson gson = new Gson();
        HttpResponseWrapper<Object> response = sendHttpRequest(RiotLinksProvider.ChampionLinkProvider.getDDragonChampionsURL(patchVersion));

        /// keys: type, version, format, data[]
        Map<String, Object> wrappedObjects = response.getResponse() != null ? (Map<String, Object>) response.getResponse() :
                Collections.emptyMap();

        if (!wrappedObjects.containsKey(DataDragonChampionKeyEnum.DATA.getValue())) {
            return new HttpResponseWrapper(false, null, response.getResponseMessage(), response.getStatusCode());
        }

        Map<String, Object> championsByName = getChampionsByDataDragonEnum(wrappedObjects, DataDragonChampionKeyEnum.DATA);

        List<ChampionItem> items = championsByName.entrySet().stream().map(entry -> {
            var json = gson.toJson(entry.getValue());
            var cItem = gson.fromJson(json, ChampionItem.class);
            return cItem;
        }).collect(Collectors.toList());
        return new HttpResponseWrapper(response.isSuccess(), items, response.getResponse().toString(), response.getStatusCode());
    }

    private Map<String, Object> getChampionsByDataDragonEnum(Map<String, Object> wrappedObjects, DataDragonChampionKeyEnum dataDragonItemKeyEnum) {
        return (Map<String, Object>) wrappedObjects.get(dataDragonItemKeyEnum.getValue());
    }


    private HttpResponseWrapper<Object> sendHttpRequest(String url) {
        return httpClient.get(url, Object.class);
    }
}
