package app.sdkgen.client;

import app.sdkgen.client.Exception.ClientException;
import app.sdkgen.client.generated.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

public class IntegrationTest {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.objectMapper = new ObjectMapper();

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

        String[] arrayScalar = {"foo", "bar"};
        TestObject[] arrayObject = {objectFoo, objectBar};

        TestRequest payload = new TestRequest();
        payload.setInt(1337);
        payload.setFloat((float) 13.37);
        payload.setString("foobar");
        payload.setBool(true);
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
