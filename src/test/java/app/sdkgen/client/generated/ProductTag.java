/**
 * ProductTag automatically generated by SDKgen please do not edit this file manually
 * @see https://sdkgen.app
 */

package app.sdkgen.client.generated;

import app.sdkgen.client.Exception.ClientException;
import app.sdkgen.client.Exception.UnknownStatusCodeException;
import app.sdkgen.client.Parser;
import app.sdkgen.client.TagAbstract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.*;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTag extends TagAbstract {
    public ProductTag(HttpClient httpClient, ObjectMapper objectMapper, Parser parser) {
        super(httpClient, objectMapper, parser);
    }


    /**
     * Returns a collection
     */
    public TestResponse getAll(Integer startIndex, Integer count, String search) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("startIndex", startIndex);
            queryParams.put("count", count);
            queryParams.put("search", search);

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpGet request = new HttpGet(builder.build());


            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Creates a new product
     */
    public TestResponse create(TestRequest payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new StringEntity(this.objectMapper.writeValueAsString(payload), ContentType.APPLICATION_JSON));

            request.setHeader("Content-Type", "application/json");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    throw new TestResponseException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing product
     */
    public TestResponse update(Integer id, TestRequest payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();
            pathParams.put("id", id);

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/:id", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPut request = new HttpPut(builder.build());
            request.setEntity(new StringEntity(this.objectMapper.writeValueAsString(payload), ContentType.APPLICATION_JSON));

            request.setHeader("Content-Type", "application/json");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Patches an existing product
     */
    public TestResponse patch(Integer id, TestRequest payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();
            pathParams.put("id", id);

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/:id", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPatch request = new HttpPatch(builder.build());
            request.setEntity(new StringEntity(this.objectMapper.writeValueAsString(payload), ContentType.APPLICATION_JSON));

            request.setHeader("Content-Type", "application/json");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes an existing product
     */
    public TestResponse delete(Integer id) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();
            pathParams.put("id", id);

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/:id", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpDelete request = new HttpDelete(builder.build());


            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Test binary content type
     */
    public TestResponse binary(byte[] payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/binary", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new ByteArrayEntity(payload, ContentType.create("application/octet-stream")));

            request.setHeader("Content-Type", "application/octet-stream");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    var data = EntityUtils.toByteArray(response.getEntity());

                    throw new BinaryException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Test form content type
     */
    public TestResponse form(List<org.apache.hc.core5.http.NameValuePair> payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/form", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new UrlEncodedFormEntity(payload));

            request.setHeader("Content-Type", "application/x-www-form-urlencoded");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    var data = URLEncodedUtils.parse(EntityUtils.toString(response.getEntity()), StandardCharsets.UTF_8);

                    throw new FormException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Test json content type
     */
    public TestResponse json(Object payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/json", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new StringEntity(this.objectMapper.writeValueAsString(payload), ContentType.APPLICATION_JSON));

            request.setHeader("Content-Type", "application/json");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), Object.class);

                    throw new JsonException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Test json content type
     */
    public TestResponse multipart(org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/multipart", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(payload.build());


            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    // @TODO currently not possible, please create an issue at https://github.com/apioo/typeapi if needed
                    var data = org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder.create();

                    throw new MultipartException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Test text content type
     */
    public TestResponse text(String payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/text", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new StringEntity(payload, ContentType.create("text/plain")));

            request.setHeader("Content-Type", "text/plain");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    var data = EntityUtils.toString(response.getEntity());

                    throw new TextException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }

    /**
     * Test xml content type
     */
    public TestResponse xml(String payload) throws ClientException {
        try {
            Map<String, Object> pathParams = new HashMap<>();

            Map<String, Object> queryParams = new HashMap<>();

            List<String> queryStructNames = new ArrayList<>();

            URIBuilder builder = new URIBuilder(this.parser.url("/anything/xml", pathParams));
            this.parser.query(builder, queryParams, queryStructNames);

            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new StringEntity(payload, ContentType.create("application/xml")));

            request.setHeader("Content-Type", "application/xml");

            return this.httpClient.execute(request, response -> {
                if (response.getCode() >= 200 && response.getCode() <= 299) {
                    var data = this.parser.parse(EntityUtils.toString(response.getEntity()), new TypeReference<TestResponse>(){});

                    return data;
                }

                var statusCode = response.getCode();
                if (statusCode == 500) {
                    var data = EntityUtils.toString(response.getEntity());

                    throw new XmlException(data);
                }

                throw new UnknownStatusCodeException("The server returned an unknown status code: " + statusCode);
            });
        } catch (URISyntaxException | IOException e) {
            throw new ClientException("An unknown error occurred: " + e.getMessage(), e);
        }
    }



}
