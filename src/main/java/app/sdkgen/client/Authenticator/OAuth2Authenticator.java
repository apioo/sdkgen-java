package app.sdkgen.client.Authenticator;

import app.sdkgen.client.*;
import app.sdkgen.client.Credentials.HttpBasic;
import app.sdkgen.client.Credentials.HttpBearer;
import app.sdkgen.client.Credentials.OAuth2;
import app.sdkgen.client.Exception.Authenticator.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OAuth2Authenticator implements AuthenticatorInterface {
    private static final int EXPIRE_THRESHOLD = 60 * 10;

    private final OAuth2 credentials;
    private final TokenStoreInterface tokenStore;
    private final List<String> scopes;

    public OAuth2Authenticator(OAuth2 credentials) {
        this.credentials = credentials;
        this.tokenStore = credentials.getTokenStore();
        this.scopes = credentials.getScopes();
    }

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) {
        try {
            httpRequest.addHeader("Authorization", "Bearer " + this.getAccessToken());
        } catch (FoundNoAccessTokenException | AccessTokenRequestException | InvalidAccessTokenException | TokenPersistException | InvalidCredentialsException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Could not obtain access token", e);
        }
    }

    public String buildRedirectUrl(String redirectUrl, List<String> scopes, String state) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(this.credentials.getAuthorizationUrl());
        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", this.credentials.getClientId());

        if (redirectUrl != null && !redirectUrl.isEmpty()) {
            builder.addParameter("redirect_uri", redirectUrl);
        }

        if (scopes != null && !scopes.isEmpty()) {
            builder.addParameter("scope", String.join(",", scopes));
        } else if (this.scopes != null && !this.scopes.isEmpty()) {
            builder.addParameter("scope", String.join(",", this.scopes));
        }

        if (state != null && !state.isEmpty()) {
            builder.addParameter("state", state);
        }

        return builder.toString();
    }

    protected AccessToken fetchAccessTokenByCode(String code) throws AccessTokenRequestException, InvalidCredentialsException, InvalidAccessTokenException, TokenPersistException {
        try {
            app.sdkgen.client.Credentials.HttpBasic credentials = new app.sdkgen.client.Credentials.HttpBasic(this.credentials.getClientId(), this.credentials.getClientSecret());

            HttpPost request = new HttpPost(this.credentials.getTokenUrl());
            request.addHeader("Accept", "application/json");

            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
            parameters.add(new BasicNameValuePair("code", code));
            request.setEntity(new UrlEncodedFormEntity(parameters));

            return this.request(credentials, request);
        } catch (IOException e) {
            throw new AccessTokenRequestException("Could not request access token: " + e.getMessage(), e);
        }
    }

    protected AccessToken fetchAccessTokenByClientCredentials() throws AccessTokenRequestException, InvalidCredentialsException, InvalidAccessTokenException, TokenPersistException {
        try {
            app.sdkgen.client.Credentials.HttpBasic credentials = new HttpBasic(this.credentials.getClientId(), this.credentials.getClientSecret());

            HttpPost request = new HttpPost(this.credentials.getTokenUrl());
            request.addHeader("Accept", "application/json");

            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", "client_credentials"));

            if (this.scopes != null && !this.scopes.isEmpty()) {
                parameters.add(new BasicNameValuePair("scope", String.join(",", this.scopes)));
            }

            request.setEntity(new UrlEncodedFormEntity(parameters));

            return this.request(credentials, request);
        } catch (IOException e) {
            throw new AccessTokenRequestException("Could not request access token: " + e.getMessage(), e);
        }
    }

    protected AccessToken fetchAccessTokenByRefresh(String refreshToken) throws AccessTokenRequestException, FoundNoAccessTokenException, InvalidCredentialsException, InvalidAccessTokenException, TokenPersistException {
        try {
            app.sdkgen.client.Credentials.HttpBearer credentials = new HttpBearer(this.getAccessToken(false, 0));

            HttpPost request = new HttpPost(this.credentials.getTokenUrl());
            request.addHeader("Accept", "application/json");

            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("grant_type", "refresh_token"));
            parameters.add(new BasicNameValuePair("refresh_token", refreshToken));
            request.setEntity(new UrlEncodedFormEntity(parameters));

            return this.request(credentials, request);
        } catch (IOException e) {
            throw new AccessTokenRequestException("Could not request access token: " + e.getMessage(), e);
        }
    }

    protected String getAccessToken(boolean automaticRefresh, int expireThreshold) throws AccessTokenRequestException, FoundNoAccessTokenException, InvalidAccessTokenException, TokenPersistException, InvalidCredentialsException {
        long timestamp = System.currentTimeMillis() / 1000;

        AccessToken accessToken = this.tokenStore.get();
        if (accessToken == null || accessToken.getExpiresIn() < timestamp) {
            accessToken = this.fetchAccessTokenByClientCredentials();
        }

        if (accessToken == null) {
            throw new FoundNoAccessTokenException("Found no access token, please obtain an access token before making a request");
        }

        if (accessToken.getExpiresIn() > (timestamp + expireThreshold)) {
            return accessToken.getAccessToken();
        }

        if (automaticRefresh && accessToken.hasRefreshToken()) {
            accessToken = this.fetchAccessTokenByRefresh(accessToken.getRefreshToken());
        }

        return accessToken.getAccessToken();
    }

    protected String getAccessToken() throws FoundNoAccessTokenException, AccessTokenRequestException, InvalidAccessTokenException, TokenPersistException, InvalidCredentialsException {
        return this.getAccessToken(true, EXPIRE_THRESHOLD);
    }

    private AccessToken parseTokenResponse(String body) throws TokenPersistException, AccessTokenRequestException {
        try {
            AccessToken token = (new ObjectMapper()).readValue(body, AccessToken.class);

            if (this.tokenStore != null) {
                this.tokenStore.persist(token);
            }

            return token;
        } catch (IOException e) {
            throw new AccessTokenRequestException("Could not parse access token response " + e.getMessage(), e);
        }
    }

    private CloseableHttpClient newHttpClient(CredentialsInterface credentials) throws InvalidCredentialsException {
        return (new HttpClientFactory(AuthenticatorFactory.factory(credentials))).factory();
    }

    private AccessToken request(CredentialsInterface credentials, HttpPost request) throws InvalidCredentialsException, IOException, TokenPersistException, AccessTokenRequestException {
        CloseableHttpClient httpClient = this.newHttpClient(credentials);
        String response = httpClient.execute(request, new BasicHttpClientResponseHandler());

        AccessToken token = this.parseTokenResponse(response);

        httpClient.close();

        return token;
    }
}
