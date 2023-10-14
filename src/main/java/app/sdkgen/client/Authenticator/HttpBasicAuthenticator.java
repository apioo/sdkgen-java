package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.HttpBasic;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.util.Base64;

public class HttpBasicAuthenticator implements AuthenticatorInterface {
    private final HttpBasic credentials;

    public HttpBasicAuthenticator(HttpBasic credentials) {
        this.credentials = credentials;
    }

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) {
        httpRequest.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((this.credentials.getUserName() + ":" + this.credentials.getPassword()).getBytes()));
    }
}
