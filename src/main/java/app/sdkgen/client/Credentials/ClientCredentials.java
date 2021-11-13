package app.sdkgen.client.Credentials;

public class ClientCredentials extends OAuth2Abstract {
    public ClientCredentials(String clientId, String clientSecret, String tokenUrl, String authorizationUrl, String refreshUrl) {
        super(clientId, clientSecret, tokenUrl, authorizationUrl, refreshUrl);
    }
}
