
package app.sdkgen.client;

import org.apache.http.client.HttpClient;

public abstract class TagAbstract {
    protected HttpClient httpClient;
    protected Parser parser;

    public TagAbstract(HttpClient httpClient, Parser parser) {
        this.httpClient = httpClient;
        this.parser = parser;
    }
}
