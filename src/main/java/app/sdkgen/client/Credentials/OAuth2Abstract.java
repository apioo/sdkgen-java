package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;

public abstract class OAuth2Abstract implements CredentialsInterface {
    private final String clientId;
    private final String clientSecret;
    private final String tokenUrl;
    private final String authorizationUrl;
    private final String refreshUrl;

    public OAuth2Abstract(String clientId, String clientSecret, String tokenUrl, String authorizationUrl, String refreshUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUrl = tokenUrl;
        this.authorizationUrl = authorizationUrl;
        this.refreshUrl = refreshUrl;
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

    public String getRefreshUrl() {
        return refreshUrl;
    }
}
