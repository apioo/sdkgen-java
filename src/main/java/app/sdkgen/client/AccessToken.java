package app.sdkgen.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {
    private final String accessToken;
    private final String tokenType;
    private final int expiresIn;
    private final String refreshToken;
    private final String scope;
    @JsonCreator
    public AccessToken(@JsonProperty("access_token") String accessToken, @JsonProperty("token_type") String tokenType, @JsonProperty("expires_in") int expiresIn, @JsonProperty("refresh_token") String refreshToken, @JsonProperty("scope") String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public boolean hasRefreshToken() {
        return this.refreshToken != null && !this.refreshToken.isEmpty();
    }
}
