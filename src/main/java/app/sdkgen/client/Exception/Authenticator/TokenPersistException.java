package app.sdkgen.client.Exception.Authenticator;

import app.sdkgen.client.Exception.ClientException;

public class TokenPersistException extends ClientException {
    public TokenPersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
