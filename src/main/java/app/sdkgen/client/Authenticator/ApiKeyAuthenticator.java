package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.ApiKey;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;

public class ApiKeyAuthenticator implements AuthenticatorInterface {

    private final ApiKey credentials;

    public ApiKeyAuthenticator(ApiKey credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) {
        httpRequest.addHeader(this.credentials.getName(), this.credentials.getToken());
    }
}
