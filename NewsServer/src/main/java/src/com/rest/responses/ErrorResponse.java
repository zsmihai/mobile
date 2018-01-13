package src.com.rest.responses;

public class ErrorResponse implements Response {
    public int error;
    public String message;

    public ErrorResponse(int error, String message) {
        this.error = error;
        this.message = message;
    }
}
