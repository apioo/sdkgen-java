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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppTest {

    @Test
    public void testParse() throws JsonProcessingException {
        String json = "{\"id\": 2, \"userId\": 1, \"status\": 1, \"name\": \"Consumer\", \"appKey\": \"9b15b481-6449-4dd2-866a-a0c1a4fa5bad\", \"date\": \"2022-08-27T00:00:48Z\"}";

        ObjectMapper objectMapper = (new ObjectMapper()).findAndRegisterModules();
        App app = objectMapper.readValue(json, App.class);

        Assert.assertEquals(2, app.getId().intValue());
        Assert.assertEquals(1, app.getUserId().intValue());
        Assert.assertEquals(1, app.getStatus().intValue());
        Assert.assertEquals("Consumer", app.getName());
        Assert.assertEquals("9b15b481-6449-4dd2-866a-a0c1a4fa5bad", app.getAppKey());
        Assert.assertEquals(LocalDateTime.parse("2022-08-27T00:00:48Z", DateTimeFormatter.ISO_DATE_TIME), app.getDate());
    }

}
