package leagueoflegendsproject.Helpers;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiotHttpClient implements IRiotHttpClient {

    @Value("${riot.api.headerApiKey}")
    private String headerApiKey;
    @Value("${riot.api.key}")
    private String riotApiKey;
    private final RestTemplate restTemplate;

    public <T> HttpResponseWrapper<T> get(String url, Class<T> responseClass) {
        log.info("========== HTTP REQUEST STARTED FOR URL: {} ==========", url);

        HttpHeaders headers = new HttpHeaders();
        headers.add(headerApiKey, riotApiKey);

        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(url, String.class);
        } catch (HttpStatusCodeException ex) {
            log.error("INVALID HTTP REQUEST URL {}", url);
            return new HttpResponseWrapper<>(false, null, ex.getResponseBodyAsString(), ex.getRawStatusCode());
        }

        if (responseEntity.getStatusCodeValue() < 300) {
            GsonBuilder gson = new GsonBuilder();
            T jsonResponse = gson.create().fromJson(responseEntity.getBody(), responseClass);
            return new HttpResponseWrapper<>(true, jsonResponse, responseEntity.getBody(), responseEntity.getStatusCodeValue());
        }
        return new HttpResponseWrapper<>(false, null, responseEntity.getBody(), responseEntity.getStatusCodeValue());
    }

    public static <T> T parseResponseToClassObject(String responseBody, Class<T> responseClass) {
        GsonBuilder gson = new GsonBuilder();
        return gson.create().fromJson(responseBody, responseClass);
    }

    public static <T> T parseResponseToClassObject(String responseBody, TypeToken<T> type) {
        GsonBuilder gson = new GsonBuilder();
        return gson.create().fromJson(responseBody, type.getType());
    }
}
