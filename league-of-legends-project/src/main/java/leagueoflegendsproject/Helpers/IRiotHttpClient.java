package leagueoflegendsproject.Helpers;

import java.io.IOException;

public interface IRiotHttpClient {
    public <T> HttpResponseWrapper<T> get(String url, Class<T> responseClass) throws IOException, InterruptedException;
    public <T> HttpResponseWrapper put(String url, T data) throws IOException, InterruptedException;
    public <T> HttpResponseWrapper post(String url, T data) throws IOException, InterruptedException;
    public <T, TResponse> HttpResponseWrapper<TResponse> postWithResponse(String url, T data) throws IOException, InterruptedException;
}
