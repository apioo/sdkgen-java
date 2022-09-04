package app.sdkgen.client.Credentials;

public class ClientCredentials extends OAuth2Abstract {
    public ClientCredentials(String clientId, String clientSecret, String tokenUrl, String authorizationUrl) {
        super(clientId, clientSecret, tokenUrl, authorizationUrl);
    }
}
