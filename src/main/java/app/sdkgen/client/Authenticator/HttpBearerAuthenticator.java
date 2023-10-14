package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.HttpBearer;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;

public class HttpBearerAuthenticator implements AuthenticatorInterface {

    private final HttpBearer credentials;

    public HttpBearerAuthenticator(HttpBearer credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) {
        httpRequest.addHeader("Authorization", "Bearer " + this.credentials.getToken());
    }
}
