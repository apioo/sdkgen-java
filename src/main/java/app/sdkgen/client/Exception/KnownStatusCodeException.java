package app.sdkgen.client.Exception;

public class KnownStatusCodeException extends ClientException {
    public KnownStatusCodeException(String message) {
        super(message);
    }

    public KnownStatusCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
