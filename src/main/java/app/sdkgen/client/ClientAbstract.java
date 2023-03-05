
package app.sdkgen.client;

import app.sdkgen.client.Credentials.Anonymous;
import app.sdkgen.client.Exception.Authenticator.InvalidCredentialsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

public abstract class ClientAbstract {
    public static final String USER_AGENT = "SDKgen Client v1.0";

    protected AuthenticatorInterface authenticator;
    protected HttpClient httpClient;
    protected ObjectMapper objectMapper;
    protected Parser parser;

    public ClientAbstract(String baseUrl, CredentialsInterface credentials) throws InvalidCredentialsException {
        this.authenticator = AuthenticatorFactory.factory(credentials);
        this.httpClient = (new HttpClientFactory(this.authenticator)).factory();
        this.objectMapper = (new ObjectMapper()).findAndRegisterModules();
        this.parser = new Parser(baseUrl, this.objectMapper);
    }

    public ClientAbstract(String baseUrl) throws InvalidCredentialsException {
        this(baseUrl, new Anonymous());
    }

    public AuthenticatorInterface getAuthenticator() {
        return this.authenticator;
    }
}
