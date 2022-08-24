
package app.sdkgen.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

public abstract class ResourceAbstract {
    protected String baseUrl;
    protected HttpClient httpClient;
    protected ObjectMapper objectMapper;

    public ResourceAbstract(String baseUrl, HttpClient httpClient, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    protected <T> T parse(String data, Class<T> type) throws JsonProcessingException {
        return this.objectMapper.readValue(data, type);
    }
}
