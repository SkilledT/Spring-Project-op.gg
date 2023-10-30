package leagueoflegendsproject.Helpers;

import java.io.IOException;

public interface IRiotHttpClient {
    <T> HttpResponseWrapper<T> get(String url, Class<T> responseClass) throws IOException, InterruptedException;
    <T> HttpResponseWrapper put(String url, T data) throws IOException, InterruptedException;
    <T> HttpResponseWrapper post(String url, T data) throws IOException, InterruptedException;
    <T, TResponse> HttpResponseWrapper<TResponse> postWithResponse(String url, T data) throws IOException, InterruptedException;
}
