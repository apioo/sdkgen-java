/**
 * Client automatically generated by SDKgen please do not edit this file manually
 * @see https://sdkgen.app
 */

package app.sdkgen.client.generated;

import app.sdkgen.client.ClientAbstract;
import app.sdkgen.client.Credentials.*;
import app.sdkgen.client.CredentialsInterface;
import app.sdkgen.client.Exception.Authenticator.InvalidCredentialsException;
import app.sdkgen.client.Exception.ClientException;
import app.sdkgen.client.Exception.UnknownStatusCodeException;
import app.sdkgen.client.Parser;
import app.sdkgen.client.TokenStoreInterface;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.*;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client extends ClientAbstract {
    public Client(String baseUrl, CredentialsInterface credentials, String version) throws InvalidCredentialsException {
        super(baseUrl, credentials, version);
    }

    public Client(String baseUrl, CredentialsInterface credentials) throws InvalidCredentialsException {
        this(baseUrl, credentials, null);
    }

    public ProductTag product()
    {
        return new ProductTag(
            this.httpClient,
            this.objectMapper,
            this.parser
        );
    }



    public static Client build(String token) throws InvalidCredentialsException
    {
        return new Client("http://127.0.0.1:8081", new HttpBearer(token), "0.1.0");
    }

    public static Client buildAnonymous() throws InvalidCredentialsException
    {
        return new Client("http://127.0.0.1:8081", new Anonymous());
    }
}
