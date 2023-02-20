package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.HttpBasic;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Base64;

public class HttpBasicAuthenticator implements AuthenticatorInterface {
    private HttpBasic credentials;

    public HttpBasicAuthenticator(HttpBasic credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((this.credentials.getUserName() + ":" + this.credentials.getPassword()).getBytes()));
    }
}
