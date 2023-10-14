/*
 * SDKgen is a powerful code generator to automatically build client SDKs for your REST API.
 * For the current version and information visit <https://sdkgen.app>
 *
 * Copyright (c) Christoph Kappestein <christoph.kappestein@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

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
        } else if (credentials instanceof OAuth2) {
            return new OAuth2Authenticator((OAuth2) credentials);
        } else if (credentials instanceof Anonymous) {
            return new AnonymousAuthenticator((Anonymous) credentials);
        } else {
            throw new InvalidCredentialsException("Could not find authenticator for credentials");
        }
    }
}
