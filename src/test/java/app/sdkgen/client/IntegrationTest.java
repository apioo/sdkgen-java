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

import app.sdkgen.client.Exception.ClientException;
import app.sdkgen.client.generated.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntegrationTest {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.objectMapper = (new ObjectMapper())
                .findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Assume.assumeTrue(portIsOpen());
    }

    @Test
    public void testClientGetAll() throws ClientException {
        Client client = Client.build("my_token");

        TestResponse response = client.product().getAll(8, 16, "foobar");

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("GET", response.getMethod());
        Assert.assertEquals("8", response.getArgs().get("startIndex"));
        Assert.assertEquals("16", response.getArgs().get("count"));
        Assert.assertEquals("foobar", response.getArgs().get("search"));
        Assert.assertNull(response.getJson());
    }

    @Test
    public void testClientCreate() throws ClientException, JsonProcessingException {
        Client client = Client.build("my_token");

        TestRequest payload = this.newPayload();
        TestResponse response = client.product().create(payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals(0, response.getArgs().size());
        Assert.assertEquals(this.objectMapper.writeValueAsString(payload), this.objectMapper.writeValueAsString(response.getJson()));
    }

    @Test
    public void testClientUpdate() throws ClientException, JsonProcessingException {
        Client client = Client.build("my_token");

        TestRequest payload = this.newPayload();
        TestResponse response = client.product().update(1, payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("PUT", response.getMethod());
        Assert.assertEquals(0, response.getArgs().size());
        Assert.assertEquals(this.objectMapper.writeValueAsString(payload), this.objectMapper.writeValueAsString(response.getJson()));
    }

    @Test
    public void testClientPatch() throws ClientException, JsonProcessingException {
        Client client = Client.build("my_token");

        TestRequest payload = this.newPayload();
        TestResponse response = client.product().patch(1, payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("PATCH", response.getMethod());
        Assert.assertEquals(0, response.getArgs().size());
        Assert.assertEquals(this.objectMapper.writeValueAsString(payload), this.objectMapper.writeValueAsString(response.getJson()));
    }

    @Test
    public void testClientDelete() throws ClientException {
        Client client = Client.build("my_token");

        TestResponse response = client.product().delete(1);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("DELETE", response.getMethod());
        Assert.assertEquals(0, response.getArgs().size());
    }

    @Test
    public void testClientBinary() throws ClientException {
        Client client = Client.build("my_token");

        byte[] payload = {0x66, 0x6F, 0x6F, 0x62, 0x61, 0x72};

        TestResponse response = client.product().binary(payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals("foobar", response.getData());
    }

    @Test
    public void testClientForm() throws ClientException {
        Client client = Client.build("my_token");

        List<NameValuePair> payload = new ArrayList<>();
        payload.add(new BasicNameValuePair("foo", "bar"));

        TestResponse response = client.product().form(payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals("bar", response.getForm().get("foo"));
    }

    @Test
    public void testClientJson() throws ClientException {
        Client client = Client.build("my_token");

        var payload = new HashMap<String, String>();
        payload.put("string", "bar");

        TestResponse response = client.product().json(payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals("bar", response.getJson().getString());
    }

    @Test
    public void testClientMultipart() throws ClientException {
        Client client = Client.build("my_token");

        byte[] body = {0x66, 0x6F, 0x6F, 0x62, 0x61, 0x72};
        MultipartEntityBuilder payload = MultipartEntityBuilder.create();
        payload.addBinaryBody("foo", body, ContentType.TEXT_PLAIN, "upload.txt");

        TestResponse response = client.product().multipart(payload);

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals("foobar", response.getFiles().get("foo"));
    }

    @Test
    public void testClientText() throws ClientException {
        Client client = Client.build("my_token");

        TestResponse response = client.product().text("foobar");

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals("foobar", response.getData());
    }

    @Test
    public void testClientXml() throws ClientException {
        Client client = Client.build("my_token");

        TestResponse response = client.product().xml("<foo>bar</foo>");

        Assert.assertEquals("Bearer my_token", response.getHeaders().get("Authorization"));
        Assert.assertEquals("application/json", response.getHeaders().get("Accept"));
        Assert.assertEquals("SDKgen Client v1.0", response.getHeaders().get("User-Agent"));
        Assert.assertEquals("POST", response.getMethod());
        Assert.assertEquals("<foo>bar</foo>", response.getData());
    }

    public TestRequest newPayload() {

        TestObject objectFoo = new TestObject();
        objectFoo.setId(1);
        objectFoo.setName("foo");

        TestObject objectBar = new TestObject();
        objectBar.setId(2);
        objectBar.setName("bar");

        TestMapScalar mapScalar = new TestMapScalar();
        mapScalar.put("foo", "bar");
        mapScalar.put("bar", "foo");

        TestMapObject mapObject = new TestMapObject();
        mapObject.put("foo", objectFoo);
        mapObject.put("bar", objectBar);

        List<String> arrayScalar = new ArrayList<>();
        arrayScalar.add("foo");
        arrayScalar.add("bar");
        List<TestObject> arrayObject = new ArrayList<>();
        arrayObject.add(objectFoo);
        arrayObject.add(objectBar);

        TestRequest payload = new TestRequest();
        payload.setInt(1337);
        payload.setFloat(13.37);
        payload.setString("foobar");
        payload.setBool(true);
        payload.setDateString(LocalDate.of(2024, 9, 22));
        payload.setDateTimeString(LocalDateTime.of(2024, 9, 22, 10, 9, 0));
        payload.setTimeString(LocalTime.of(10, 9, 0));
        payload.setArrayScalar(arrayScalar);
        payload.setArrayObject(arrayObject);
        payload.setMapScalar(mapScalar);
        payload.setMapObject(mapObject);
        payload.setObject(objectFoo);
        return payload;
    }

    private boolean portIsOpen() {
        try {
            new Socket("127.0.0.1", 8081).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
