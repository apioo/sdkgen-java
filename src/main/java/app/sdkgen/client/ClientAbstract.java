
package app.sdkgen.client;

import app.sdkgen.client.Credentials.*;
import app.sdkgen.client.Exception.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public abstract class ClientAbstract {
    private static final String USER_AGENT = "SDKgen Client v0.1";
    private static final int EXPIRE_THRESHOLD = 60 * 10;

    protected String baseUrl;
    protected CredentialsInterface credentials;
    protected TokenStoreInterface tokenStore;
    protected ObjectMapper objectMapper;

    public ClientAbstract(String baseUrl, CredentialsInterface credentials, TokenStoreInterface tokenStore) {
        this.baseUrl = baseUrl;
        this.credentials = credentials;
        this.tokenStore = tokenStore;
        this.objectMapper = new ObjectMapper();
    }

    public String buildRedirectUrl(String redirectUrl, List<String> scopes, String state) throws InvalidCredentialsException, URISyntaxException {
        if (!(this.credentials instanceof AuthorizationCode)) {
            throw new InvalidCredentialsException("The configured credentials do not support the OAuth2 authorization code flow");
        }

        AuthorizationCode authCredentials = (AuthorizationCode) this.credentials;

        URIBuilder builder = new URIBuilder(authCredentials.getAuthorizationUrl());
        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", authCredentials.getClientId());

        if (redirectUrl != null && !redirectUrl.isEmpty()) {
            builder.addParameter("redirect_uri", redirectUrl);
        }

        if (scopes != null && scopes.size() > 0) {
            builder.addParameter("scope", String.join(",", scopes));
        }

        if (state != null && !state.isEmpty()) {
            builder.addParameter("state", state);
        }

        return builder.toString();
    }

    protected AccessToken fetchAccessTokenByCode(String code) throws InvalidCredentialsException, IOException, InvalidAccessTokenException, TokenPersistException {
        if (!(this.credentials instanceof AuthorizationCode)) {
            throw new InvalidCredentialsException("The configured credentials do not support the OAuth2 authorization code flow");
        }

        AuthorizationCode cred = (AuthorizationCode) this.credentials;
        HttpBasic credentials = new HttpBasic(cred.getClientId(), cred.getClientSecret());

        HttpPost request = new HttpPost(cred.getTokenUrl());
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Accept", "application/json");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
        parameters.add(new BasicNameValuePair("code", code));
        request.setEntity(new UrlEncodedFormEntity(parameters));

        return this.parseTokenResponse(this.newHttpClient(credentials).execute(request));
    }

    protected AccessToken fetchAccessTokenByClientCredentials() throws InvalidCredentialsException, IOException, InvalidAccessTokenException, TokenPersistException {
        if (!(this.credentials instanceof ClientCredentials)) {
            throw new InvalidCredentialsException("The configured credentials do not support the OAuth2 client credentials flow");
        }

        ClientCredentials cred = (ClientCredentials) this.credentials;
        HttpBasic credentials = new HttpBasic(cred.getClientId(), cred.getClientSecret());

        HttpPost request = new HttpPost(cred.getTokenUrl());
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Accept", "application/json");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
        request.setEntity(new UrlEncodedFormEntity(parameters));

        return this.parseTokenResponse(this.newHttpClient(credentials).execute(request));
    }

    protected AccessToken fetchAccessTokenByRefresh(String refreshToken) throws InvalidCredentialsException, TokenReadException, FoundNoAccessTokenException, IOException, InvalidAccessTokenException, TokenPersistException {
        if (!(this.credentials instanceof OAuth2Abstract)) {
            throw new InvalidCredentialsException("The configured credentials do not support the OAuth2 flow");
        }

        OAuth2Abstract cred = (OAuth2Abstract) this.credentials;
        HttpBearer credentials = new HttpBearer(this.getAccessToken(false, 0));

        HttpPost request = new HttpPost(cred.getTokenUrl());
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Accept", "application/json");

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("grant_type", "refresh_token"));
        parameters.add(new BasicNameValuePair("refresh_token", refreshToken));
        request.setEntity(new UrlEncodedFormEntity(parameters));

        return this.parseTokenResponse(this.newHttpClient(credentials).execute(request));
    }

    protected String getAccessToken(boolean automaticRefresh, int expireThreshold) throws FoundNoAccessTokenException, TokenReadException {
        if (this.tokenStore == null) {
            throw new FoundNoAccessTokenException("No token store was configured");
        }

        AccessToken token = this.tokenStore.get();
        if (token == null) {
            throw new FoundNoAccessTokenException("Found no access token, please obtain an access token before making an request");
        }

        if (token.getExpiresIn() > (System.currentTimeMillis() / 1000) + expireThreshold) {
            return token.getAccessToken();
        }

        if (automaticRefresh && token.getRefreshToken() != null && !token.getRefreshToken().isEmpty()) {
            // @TODO refresh
            return null;
        } else {
            return token.getAccessToken();
        }
    }

    protected String getAccessToken() throws FoundNoAccessTokenException, TokenReadException {
        return this.getAccessToken(true, EXPIRE_THRESHOLD);
    }

    protected HttpClient newHttpClient(CredentialsInterface credentials)
    {
        HttpClientBuilder builder = HttpClientBuilder.create();

        if (credentials instanceof HttpBasic) {
            final HttpBasic cred = (HttpBasic) credentials;
            builder.addInterceptorFirst((HttpRequestInterceptor) (httpRequest, httpContext) -> httpRequest.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((cred.getUserName() + ":" + cred.getPasssword()).getBytes())));
        } else if (credentials instanceof HttpBearer) {
            final HttpBearer cred = (HttpBearer) credentials;
            builder.addInterceptorFirst((HttpRequestInterceptor) (httpRequest, httpContext) -> httpRequest.addHeader("Authorization", "Bearer " + cred.getToken()));
        } else if (credentials instanceof ApiKey) {
            final ApiKey cred = (ApiKey) credentials;
            builder.addInterceptorFirst((HttpRequestInterceptor) (httpRequest, httpContext) -> httpRequest.addHeader(cred.getName(), cred.getToken()));
        } else if (credentials instanceof OAuth2Abstract) {
            builder.addInterceptorFirst((HttpRequestInterceptor) (httpRequest, httpContext) -> {
                try {
                    httpRequest.addHeader("Authorization", "Bearer " + this.getAccessToken());
                } catch (FoundNoAccessTokenException | TokenReadException e) {
                    // @TODO there is not much we can do here
                }
            });
        }

        return builder.build();
    }

    protected HttpClient newHttpClient() {
        return this.newHttpClient(this.credentials);
    }

    private AccessToken parseTokenResponse(HttpResponse response) throws InvalidAccessTokenException, IOException, TokenPersistException {
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new InvalidAccessTokenException("Could not obtain access token");
        }

        String body = EntityUtils.toString(response.getEntity());
        AccessToken token = (new ObjectMapper()).readValue(body, AccessToken.class);

        if (this.tokenStore != null) {
            this.tokenStore.persist(token);
        }

        return token;
    }
}
