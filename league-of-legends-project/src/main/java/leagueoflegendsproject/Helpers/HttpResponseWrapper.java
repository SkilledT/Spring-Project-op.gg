package leagueoflegendsproject.Helpers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponseWrapper<T> {
    private boolean success;
    private T response;
    public String responseMessage;
    public int statusCode;
}
