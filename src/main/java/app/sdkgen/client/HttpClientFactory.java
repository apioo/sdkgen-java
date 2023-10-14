package app.sdkgen.client;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;

public class HttpClientFactory {

    private final AuthenticatorInterface authenticator;

    public HttpClientFactory(AuthenticatorInterface authenticator) {
        this.authenticator = authenticator;
    }

    public CloseableHttpClient factory() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.addInterceptorFirst(this.authenticator);
        builder.addInterceptorFirst(new DefaultInterceptor());

        return builder.build();
    }

    private static class DefaultInterceptor implements HttpRequestInterceptor {
        @Override
        public void process(HttpRequest httpRequest, HttpContext httpContext) {
            httpRequest.addHeader("User-Agent", ClientAbstract.USER_AGENT);
            httpRequest.addHeader("Accept", "application/json");
        }
    }
}
