/*
 * SDKgen is a powerful code generator to automatically build client SDKs for your REST API.
 * For the current version and information visit <https://sdkgen.app>
 *
 * Copyright (c) Christoph Kappestein <christoph.kappestein@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;
import app.sdkgen.client.TokenStore.MemoryTokenStore;
import app.sdkgen.client.TokenStoreInterface;

import java.util.ArrayList;
import java.util.List;

public class OAuth2 implements CredentialsInterface {
    private final String clientId;
    private final String clientSecret;
    private final String tokenUrl;
    private final String authorizationUrl;
    private final TokenStoreInterface tokenStore;
    private final List<String> scopes;

    public OAuth2(String clientId, String clientSecret, String tokenUrl, String authorizationUrl, TokenStoreInterface tokenStore, List<String> scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUrl = tokenUrl;
        this.authorizationUrl = authorizationUrl;
        this.tokenStore = tokenStore;
        this.scopes = scopes;
    }

    public OAuth2(String clientId, String clientSecret, String tokenUrl, String authorizationUrl) {
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
