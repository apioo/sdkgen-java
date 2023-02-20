
package app.sdkgen.client;

import app.sdkgen.client.Credentials.*;
import app.sdkgen.client.Exception.Authenticator.InvalidCredentialsException;

public class AuthenticatorFactory {
    public static AuthenticatorInterface factory(CredentialsInterface credentials) throws InvalidCredentialsException {
        if (credentials instanceof HttpBasic) {
            return new app.sdkgen.client.Authenticator.HttpBasic((HttpBasic) credentials);
        } else if (credentials instanceof HttpBearer) {
            return new app.sdkgen.client.Authenticator.HttpBearer((HttpBearer) credentials);
        } else if (credentials instanceof ApiKey) {
            return new app.sdkgen.client.Authenticator.ApiKey((ApiKey) credentials);
        } else if (credentials instanceof OAuth2Abstract) {
            return new app.sdkgen.client.Authenticator.OAuth2((OAuth2Abstract) credentials);
        } else if (credentials instanceof Anonymous) {
            return new app.sdkgen.client.Authenticator.Anonymous((Anonymous) credentials);
        } else {
            throw new InvalidCredentialsException("Could not find authenticator for credentials");
        }
    }
}
