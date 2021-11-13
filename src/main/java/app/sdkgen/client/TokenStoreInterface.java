
package app.sdkgen.client;

import app.sdkgen.client.Exception.TokenPersistException;
import app.sdkgen.client.Exception.TokenReadException;

public interface TokenStoreInterface {
    AccessToken get() throws TokenReadException;

    void persist(AccessToken token) throws TokenPersistException;
}
