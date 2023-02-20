package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Base64;

public class HttpBasic implements AuthenticatorInterface {
    private app.sdkgen.client.Credentials.HttpBasic credentials;

    public HttpBasic(app.sdkgen.client.Credentials.HttpBasic credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((this.credentials.getUserName() + ":" + this.credentials.getPassword()).getBytes()));
    }
}
