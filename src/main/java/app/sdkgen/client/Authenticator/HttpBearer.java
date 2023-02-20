package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class HttpBearer implements AuthenticatorInterface {

    private app.sdkgen.client.Credentials.HttpBearer credentials;

    public HttpBearer(app.sdkgen.client.Credentials.HttpBearer credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.addHeader("Authorization", "Bearer " + this.credentials.getToken());
    }
}
