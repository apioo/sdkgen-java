
package app.sdkgen.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;

public abstract class TagAbstract {
    protected HttpClient httpClient;
    protected ObjectMapper objectMapper;
    protected Parser parser;

    public TagAbstract(HttpClient httpClient, ObjectMapper objectMapper, Parser parser) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.parser = parser;
    }
}
