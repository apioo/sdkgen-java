package app.sdkgen.client.Exception.Authenticator;

import app.sdkgen.client.Exception.ClientException;

public class InvalidAccessTokenException extends ClientException {
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
