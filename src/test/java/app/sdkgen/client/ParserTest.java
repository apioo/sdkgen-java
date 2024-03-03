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

import app.sdkgen.client.dto.App;
import app.sdkgen.client.generated.TestObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParserTest {

    @Test
    public void testUrl() {
        Parser parser = new Parser("https://api.acme.com/", new ObjectMapper());

        Assert.assertEquals("https://api.acme.com/foo/bar", parser.url("/foo/bar", new HashMap<>()));
        Assert.assertEquals("https://api.acme.com/foo/foo", parser.url("/foo/:bar", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo", parser.url("/foo/$bar<[0-9]+>", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo", parser.url("/foo/$bar", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo", parser.url("/foo/{bar}", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo/bar", parser.url("/foo/{bar}/bar", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo/bar", parser.url("/foo/$bar<[0-9]+>/bar", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo/bar", parser.url("/foo/$bar/bar", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/foo/bar", parser.url("/foo/{bar}/bar", this.newMap("bar", "foo")));

        Assert.assertEquals("https://api.acme.com/foo/", parser.url("/foo/{bar}", this.newMap("bar", null)));
        Assert.assertEquals("https://api.acme.com/foo/1337", parser.url("/foo/{bar}", this.newMap("bar", 1337)));
        Assert.assertEquals("https://api.acme.com/foo/13.37", parser.url("/foo/{bar}", this.newMap("bar", 13.37)));
        Assert.assertEquals("https://api.acme.com/foo/1", parser.url("/foo/{bar}", this.newMap("bar", true)));
        Assert.assertEquals("https://api.acme.com/foo/0", parser.url("/foo/{bar}", this.newMap("bar", false)));
        Assert.assertEquals("https://api.acme.com/foo/foo", parser.url("/foo/{bar}", this.newMap("bar", "foo")));
        Assert.assertEquals("https://api.acme.com/foo/2023-02-21", parser.url("/foo/{bar}", this.newMap("bar", LocalDate.of(2023, 2, 21))));
        Assert.assertEquals("https://api.acme.com/foo/2023-02-21T19:19:00Z", parser.url("/foo/{bar}", this.newMap("bar", LocalDateTime.of(2023, 2, 21, 19, 19, 0))));
        Assert.assertEquals("https://api.acme.com/foo/19:19:00", parser.url("/foo/{bar}", this.newMap("bar", LocalTime.of(19, 19, 0))));
    }

    @Test
    public void testQuery() {
        Parser parser = new Parser("https://api.acme.com/", new ObjectMapper());

        TestObject test = new TestObject();
        test.setName("foo");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("null", null);
        map.put("int", 1337);
        map.put("float", 13.37);
        map.put("true", true);
        map.put("false", false);
        map.put("string", "foo");
        map.put("date", LocalDate.of(2023, 2, 21));
        map.put("datetime", LocalDateTime.of(2023, 2, 21, 19, 19, 0));
        map.put("time", LocalTime.of(19, 19, 0));
        map.put("args", test);

        URIBuilder builder = new URIBuilder();
        parser.query(builder, map, List.of("args"));

        Assert.assertEquals("int", builder.getQueryParams().get(0).getName());
        Assert.assertEquals("1337", builder.getQueryParams().get(0).getValue());
        Assert.assertEquals("float", builder.getQueryParams().get(1).getName());
        Assert.assertEquals("13.37", builder.getQueryParams().get(1).getValue());
        Assert.assertEquals("true", builder.getQueryParams().get(2).getName());
        Assert.assertEquals("1", builder.getQueryParams().get(2).getValue());
        Assert.assertEquals("false", builder.getQueryParams().get(3).getName());
        Assert.assertEquals("0", builder.getQueryParams().get(3).getValue());
        Assert.assertEquals("string", builder.getQueryParams().get(4).getName());
        Assert.assertEquals("foo", builder.getQueryParams().get(4).getValue());
        Assert.assertEquals("date", builder.getQueryParams().get(5).getName());
        Assert.assertEquals("2023-02-21", builder.getQueryParams().get(5).getValue());
        Assert.assertEquals("datetime", builder.getQueryParams().get(6).getName());
        Assert.assertEquals("2023-02-21T19:19:00Z", builder.getQueryParams().get(6).getValue());
        Assert.assertEquals("time", builder.getQueryParams().get(7).getName());
        Assert.assertEquals("19:19:00", builder.getQueryParams().get(7).getValue());
        Assert.assertEquals("name", builder.getQueryParams().get(8).getName());
        Assert.assertEquals("foo", builder.getQueryParams().get(8).getValue());
    }

    private Map<String, Object> newMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
