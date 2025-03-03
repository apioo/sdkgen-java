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

import app.sdkgen.client.Credentials.Anonymous;
import app.sdkgen.client.Exception.Authenticator.InvalidCredentialsException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;

public abstract class ClientAbstract {
    public static final String USER_AGENT = "SDKgen";

    protected AuthenticatorInterface authenticator;
    protected HttpClient httpClient;
    protected ObjectMapper objectMapper;
    protected Parser parser;

    public ClientAbstract(String baseUrl, CredentialsInterface credentials, String version) throws InvalidCredentialsException {
        this.authenticator = AuthenticatorFactory.factory(credentials);
        this.httpClient = (new HttpClientFactory(this.authenticator, version)).factory();
        this.objectMapper = (new ObjectMapper())
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.parser = new Parser(baseUrl, this.objectMapper);
    }

    public ClientAbstract(String baseUrl, CredentialsInterface credentials) throws InvalidCredentialsException {
        this(baseUrl, credentials, null);
    }

    public ClientAbstract(String baseUrl) throws InvalidCredentialsException {
        this(baseUrl, new Anonymous());
    }

    public AuthenticatorInterface getAuthenticator() {
        return this.authenticator;
    }
}
