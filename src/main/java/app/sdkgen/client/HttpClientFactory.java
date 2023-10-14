package app.sdkgen.client;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;

public class HttpClientFactory {

    private final AuthenticatorInterface authenticator;

    public HttpClientFactory(AuthenticatorInterface authenticator) {
        this.authenticator = authenticator;
    }

    public CloseableHttpClient factory() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.addRequestInterceptorFirst(this.authenticator);
        builder.addRequestInterceptorFirst(new DefaultInterceptor());

        return builder.build();
    }

    private static class DefaultInterceptor implements HttpRequestInterceptor {
        @Override
        public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {
            httpRequest.addHeader("User-Agent", ClientAbstract.USER_AGENT);
            httpRequest.addHeader("Accept", "application/json");
        }
    }
}
