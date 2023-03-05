package app.sdkgen.client.Exception;

public class UnknownStatusCodeException extends ClientException {
    public UnknownStatusCodeException(String message) {
        super(message);
    }

    public UnknownStatusCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
