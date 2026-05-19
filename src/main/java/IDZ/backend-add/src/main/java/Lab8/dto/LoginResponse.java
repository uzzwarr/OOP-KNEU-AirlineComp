package Lab8.dto;

// відповідь з jwt токеном після успішного логіну
public class LoginResponse {

    private String token;
    private String username;
    private String type = "Bearer";

    public LoginResponse() {}

    public LoginResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
