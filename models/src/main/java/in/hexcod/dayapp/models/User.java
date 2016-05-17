package in.hexcod.dayapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by sidhant on 17-05-2016.
 */
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String passwordHash;
    private String loginSecret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLoginSecret() {
        return loginSecret;
    }

    public void setLoginSecret(String loginSecret) {
        this.loginSecret = loginSecret;
    }
}
