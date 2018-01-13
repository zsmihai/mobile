package src.com.rest.responses;

public class SuccessResponse implements Response {
    public final int error_code = 0;
    public final Object response;

    public SuccessResponse(Object response) {
        this.response = response;
    }
}
