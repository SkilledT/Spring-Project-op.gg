package leagueoflegendsproject.Helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.sun.jdi.request.InvalidRequestStateException;
import leagueoflegendsproject.Models.LoLApi.Champions.ChampionItem;
import leagueoflegendsproject.Models.LoLApi.Items.Item;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RiotHttpClient implements IRiotHttpClient {

    private final String headerApiKey = "X-Riot-Token";
    private final String riotApiKey = "RGAPI-36bfbb75-b4fd-4782-bded-89ce71a3c9e0";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public RiotHttpClient() {
    }

    public <T> HttpResponseWrapper<T> get(String url, Class<T> responseClass) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header(headerApiKey, riotApiKey)
                .build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 300) {
            GsonBuilder gson = new GsonBuilder();
            T jsonResponse = gson.create().fromJson(response.body(), responseClass);
            return new HttpResponseWrapper<>(true, jsonResponse, response.body());
        }
        return new HttpResponseWrapper<T>(false, null, response.body());
    }

    public static <T> T parseResponseToClassObject(String responseBody, Class<T> responseClass) {
        GsonBuilder gson = new GsonBuilder();
        return gson.create().fromJson(responseBody, responseClass);
    }

    public static <T> T parseResponseToClassObject(String responseBody, TypeToken<T> type) {
        GsonBuilder gson = new GsonBuilder();
        return gson.create().fromJson(responseBody, type.getType());
    }

    public HttpResponseWrapper<List<ChampionItem>> getChampions() {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RiotLinksProvider.RIOT_CHAMPION_URL))
                .GET()
                .header(headerApiKey, riotApiKey)
                .build();
        HttpResponse<String> response;
        try {
            response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new InvalidRequestStateException("Unable to send request to RIOT's API");
        }

        Object o = gson.fromJson(response.body(), Object.class);
        List keys = new ArrayList();
        Collection values = null;
        if (o instanceof Map) {
            Map map = (Map) o;
            keys.addAll(map.keySet());
            values = map.values();
        } else if (o instanceof Collection) {
            values = (Collection) o;
        }

        for (var val : values) {
            if (val instanceof LinkedTreeMap) {
                Map champMap = ((LinkedTreeMap) val);
                List<ChampionItem> championItemList = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(champMap);
                Set<String> championNames = jsonObject.keySet();
                for (var cName : championNames) {
                    var cValues = champMap.get(cName);
                    var json = gson.toJson(cValues);
                    var cItem = gson.fromJson(json, ChampionItem.class);
                    championItemList.add(cItem);
                }
                return new HttpResponseWrapper<>(true, championItemList, champMap.toString());
            }
        }
        return new HttpResponseWrapper(false, null, response.body());
    }

    public HttpResponseWrapper<List<Item>> getItems() throws IOException, InterruptedException {
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RiotLinksProvider.ItemLinkProvider.RIOT_ITEMS_URL))
                .GET()
                .header(headerApiKey, riotApiKey)
                .build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Object o = gson.fromJson(response.body(), Object.class);
        List keys = new ArrayList();
        Collection values = null;
        if (o instanceof Map) {
            Map map = (Map) o;
            keys.addAll(map.keySet());
            values = map.values();
        } else if (o instanceof Collection) {
            values = (Collection) o;
        }

        int i = 0;
        for (var val : values) {
            if (val instanceof LinkedTreeMap && i == 3) {
                Map champMap = ((LinkedTreeMap) val);
                List<Item> championItemList = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(champMap);
                Set<String> championNames = jsonObject.keySet();
                for (var cName : championNames) {
                    var cValues = champMap.get(cName);
                    var json = gson.toJson(cValues);
                    Item cItem = gson.fromJson(json, Item.class);
                    cItem.setId(Integer.parseInt(cName));
                    championItemList.add(cItem);
                }
                return new HttpResponseWrapper<>(true, championItemList, champMap.toString());
            }
            i++;
        }
        return new HttpResponseWrapper(false, null, response.body());
    }

    public <T> HttpResponseWrapper put(String url, T data) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(data);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(3000))
                .header(headerApiKey, riotApiKey)
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 300)
            return new HttpResponseWrapper(true, null, response.body());
        return new HttpResponseWrapper(false, null, response.body());
    }

    public <T> HttpResponseWrapper post(String url, T data) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(data);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(3000))
                .header(headerApiKey, riotApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 300)
            return new HttpResponseWrapper(true, null, response.body());
        return new HttpResponseWrapper(false, null, response.body());
    }

    public <T, TResponse> HttpResponseWrapper<TResponse> postWithResponse(String url, T data) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String json = gson.toJson(data);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(3000))
                .header(headerApiKey, riotApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 300) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Type collectionType = new TypeToken<T>() {
            }.getType();
            TResponse jsonResponse = gsonBuilder.create().fromJson(response.body(), collectionType);
            return new HttpResponseWrapper<>(true, jsonResponse, response.body());
        }
        return new HttpResponseWrapper<>(false, null, response.body());
    }
}
