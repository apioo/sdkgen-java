package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;
import app.sdkgen.client.TokenStore.MemoryTokenStore;
import app.sdkgen.client.TokenStoreInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class OAuth2Abstract implements CredentialsInterface {
    private final String clientId;
    private final String clientSecret;
    private final String tokenUrl;
    private final String authorizationUrl;
    private final TokenStoreInterface tokenStore;
    private final List<String> scopes;

    public OAuth2Abstract(String clientId, String clientSecret, String tokenUrl, String authorizationUrl, TokenStoreInterface tokenStore, List<String> scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUrl = tokenUrl;
        this.authorizationUrl = authorizationUrl;
        this.tokenStore = tokenStore;
        this.scopes = scopes;
    }

    public OAuth2Abstract(String clientId, String clientSecret, String tokenUrl, String authorizationUrl) {
        this(clientId, clientSecret, tokenUrl, authorizationUrl, new MemoryTokenStore(), new ArrayList<>());
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public TokenStoreInterface getTokenStore() {
        return tokenStore;
    }

    public List<String> getScopes() {
        return scopes;
    }
}
