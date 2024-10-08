/**
 * TextException automatically generated by SDKgen please do not edit this file manually
 * @see https://sdkgen.app
 */

package app.sdkgen.client.generated;

import app.sdkgen.client.Exception.KnownStatusCodeException;

public class TextException extends KnownStatusCodeException {

    private String payload;

    public TextException(String payload) {
        super("The server returned an error");

        this.payload = payload;
    }

    public String getPayload() {
        return this.payload;
    }

}
