package app.sdkgen.client.Exception.Authenticator;

import app.sdkgen.client.Exception.ClientException;

public class AccessTokenRequestException extends ClientException {
    public AccessTokenRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
