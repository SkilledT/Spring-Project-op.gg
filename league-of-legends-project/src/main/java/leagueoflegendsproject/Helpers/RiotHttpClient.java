package leagueoflegendsproject.Helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RiotHttpClient implements IRiotHttpClient {

    private final String headerApiKey = "X-Riot-Token";
    private final String riotApiKey = "RGAPI-4af642fe-85f3-4d40-9d75-5a35f2827608";
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
            Type collectionType = new TypeToken<T>(){}.getType();
            TResponse jsonResponse = gsonBuilder.create().fromJson(response.body(), collectionType);
            return new HttpResponseWrapper<>(true, jsonResponse, response.body());
        }
        return new HttpResponseWrapper<>(false, null, response.body());
    }
}
