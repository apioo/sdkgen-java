
package app.sdkgen.client;

import app.sdkgen.client.Exception.TokenPersistException;

public interface TokenStoreInterface {
    AccessToken get();

    void persist(AccessToken token) throws TokenPersistException;

    void remove();
}
