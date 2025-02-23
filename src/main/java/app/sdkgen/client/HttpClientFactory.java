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

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;

public class HttpClientFactory {

    private final AuthenticatorInterface authenticator;
    private final String version;

    public HttpClientFactory(AuthenticatorInterface authenticator, String version) {
        this.authenticator = authenticator;
        this.version = version;
    }

    public HttpClientFactory(AuthenticatorInterface authenticator) {
        this(authenticator, null);
    }

    public CloseableHttpClient factory() {
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.addRequestInterceptorFirst(this.authenticator);
        builder.addRequestInterceptorFirst(new DefaultInterceptor(this.version));

        return builder.build();
    }

    private static class DefaultInterceptor implements HttpRequestInterceptor {

        private final String version;

        public DefaultInterceptor(String version) {
            this.version = version;
        }

        @Override
        public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) {
            if (this.version != null && !this.version.isEmpty()) {
                httpRequest.addHeader("User-Agent", ClientAbstract.USER_AGENT + "/" + this.version);
            } else {
                httpRequest.addHeader("User-Agent", ClientAbstract.USER_AGENT);
            }
            httpRequest.addHeader("Accept", "application/json");
        }

    }
}
