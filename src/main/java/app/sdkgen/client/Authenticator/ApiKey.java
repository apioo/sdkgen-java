package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class ApiKey implements AuthenticatorInterface {

    private app.sdkgen.client.Credentials.ApiKey credentials;

    public ApiKey(app.sdkgen.client.Credentials.ApiKey credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.addHeader(this.credentials.getName(), this.credentials.getToken());
    }
}
