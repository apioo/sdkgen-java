package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.HttpBearer;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpBearerAuthenticator implements AuthenticatorInterface {

    private HttpBearer credentials;

    public HttpBearerAuthenticator(HttpBearer credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.addHeader("Authorization", "Bearer " + this.credentials.getToken());
    }
}
