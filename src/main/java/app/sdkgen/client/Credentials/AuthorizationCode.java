package app.sdkgen.client.Credentials;

public class AuthorizationCode extends OAuth2Abstract {
    public AuthorizationCode(String clientId, String clientSecret, String tokenUrl, String authorizationUrl) {
        super(clientId, clientSecret, tokenUrl, authorizationUrl);
    }
}
