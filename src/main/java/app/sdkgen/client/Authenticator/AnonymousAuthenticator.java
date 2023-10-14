package app.sdkgen.client.Authenticator;

import app.sdkgen.client.AuthenticatorInterface;
import app.sdkgen.client.Credentials.Anonymous;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.protocol.HttpContext;

public class AnonymousAuthenticator implements AuthenticatorInterface {

    public AnonymousAuthenticator(Anonymous credentials) {
    }

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) {
    }
}
