package app.sdkgen.client.Exception.Authenticator;

import app.sdkgen.client.Exception.ClientException;

public class FoundNoAccessTokenException extends ClientException {
    public FoundNoAccessTokenException(String message) {
        super(message);
    }
}
