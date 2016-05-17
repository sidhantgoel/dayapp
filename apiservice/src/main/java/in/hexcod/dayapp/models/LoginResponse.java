package in.hexcod.dayapp.models;

/**
 * Created by sidhant on 17-05-2016.
 */
public class LoginResponse {
    private String userId;
    private String secret;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
