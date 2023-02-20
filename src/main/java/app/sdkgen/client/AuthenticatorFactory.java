
package app.sdkgen.client;

import app.sdkgen.client.Authenticator.*;
import app.sdkgen.client.Credentials.*;
import app.sdkgen.client.Exception.Authenticator.InvalidCredentialsException;

public class AuthenticatorFactory {
    public static AuthenticatorInterface factory(CredentialsInterface credentials) throws InvalidCredentialsException {
        if (credentials instanceof HttpBasic) {
            return new HttpBasicAuthenticator((HttpBasic) credentials);
        } else if (credentials instanceof HttpBearer) {
            return new HttpBearerAuthenticator((HttpBearer) credentials);
        } else if (credentials instanceof ApiKey) {
            return new ApiKeyAuthenticator((ApiKey) credentials);
        } else if (credentials instanceof OAuth2Abstract) {
            return new OAuth2Authenticator((OAuth2Abstract) credentials);
        } else if (credentials instanceof Anonymous) {
            return new AnonymousAuthenticator((Anonymous) credentials);
        } else {
            throw new InvalidCredentialsException("Could not find authenticator for credentials");
        }
    }
}
