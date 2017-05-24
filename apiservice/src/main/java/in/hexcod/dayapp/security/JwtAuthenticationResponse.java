package in.hexcod.dayapp.security;

import java.io.Serializable;

/**
 * Created by sidhant on 24/05/17.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}