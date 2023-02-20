package app.sdkgen.client;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientFactory {

    private AuthenticatorInterface authenticator;

    public HttpClientFactory(AuthenticatorInterface authenticator) {
        this.authenticator = authenticator;
    }

    public CloseableHttpClient factory() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.addInterceptorFirst(this.authenticator);
        builder.addInterceptorFirst((HttpRequestInterceptor) (httpRequest, httpContext) -> httpRequest.addHeader("User-Agent", ClientAbstract.USER_AGENT));

        return builder.build();
    }
}
