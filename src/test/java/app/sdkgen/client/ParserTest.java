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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

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

    private HashMap<String, Object> newMap(String key, Object value) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
