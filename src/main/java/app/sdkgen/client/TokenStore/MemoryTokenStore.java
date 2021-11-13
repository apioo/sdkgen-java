package app.sdkgen.client.TokenStore;

import app.sdkgen.client.AccessToken;
import app.sdkgen.client.TokenStoreInterface;

public class MemoryTokenStore implements TokenStoreInterface {
    private AccessToken token;

    @Override
    public AccessToken get() {
        return this.token;
    }

    @Override
    public void persist(AccessToken token) {
        this.token = token;
    }
}
