package leagueoflegendsproject.Helpers;

public class HttpResponseWrapper<T> {
    private boolean success;
    private T response;
    public String responseMessage;

    public HttpResponseWrapper(boolean success, T response, String responseMessage) {
        this.success = success;
        this.response = response;
        this.responseMessage = responseMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
