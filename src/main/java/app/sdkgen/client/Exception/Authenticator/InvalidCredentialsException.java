package app.sdkgen.client.Exception.Authenticator;

import app.sdkgen.client.Exception.ClientException;

public class InvalidCredentialsException extends ClientException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
