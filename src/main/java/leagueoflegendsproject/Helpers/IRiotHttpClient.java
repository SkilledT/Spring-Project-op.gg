package leagueoflegendsproject.Helpers;

import java.io.IOException;

public interface IRiotHttpClient {
    <T> HttpResponseWrapper<T> get(String url, Class<T> responseClass) throws IOException, InterruptedException;
}
