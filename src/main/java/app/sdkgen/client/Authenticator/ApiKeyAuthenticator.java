package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.ApiKey;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class ApiKeyAuthenticator implements AuthenticatorInterface {

    private ApiKey credentials;

    public ApiKeyAuthenticator(ApiKey credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.addHeader(this.credentials.getName(), this.credentials.getToken());
    }
}
