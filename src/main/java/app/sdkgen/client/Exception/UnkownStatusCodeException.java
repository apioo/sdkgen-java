package app.sdkgen.client.Exception;

public class UnkownStatusCodeException extends ClientException {
    public UnkownStatusCodeException(String message) {
        super(message);
    }

    public UnkownStatusCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
