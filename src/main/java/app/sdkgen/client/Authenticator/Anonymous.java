package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class Anonymous implements AuthenticatorInterface {

    private app.sdkgen.client.Credentials.Anonymous credentials;

    public Anonymous(app.sdkgen.client.Credentials.Anonymous credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
    }
}
