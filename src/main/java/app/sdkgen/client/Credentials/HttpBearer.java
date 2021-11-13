package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;

public class HttpBearer implements CredentialsInterface {
    private final String token;

    public HttpBearer(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
