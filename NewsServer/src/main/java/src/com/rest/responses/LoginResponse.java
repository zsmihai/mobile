package src.com.rest.responses;

public class LoginResponse implements Response {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
