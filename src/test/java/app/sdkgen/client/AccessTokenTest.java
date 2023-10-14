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
import org.junit.*;

public class AccessTokenTest {

    @Test
    public void testParse() throws JsonProcessingException {
        String json = "{\"access_token\": \"eyJ0eXAiOiJKV1Qi\",\"token_type\": \"bearer\",\"expires_in\": 1661760660,\"refresh_token\": \"9aba2490f1801af0\",\"scope\": \"backend,backend.account\"}";

        AccessToken token = (new ObjectMapper()).readValue(json, AccessToken.class);

        Assert.assertEquals("eyJ0eXAiOiJKV1Qi", token.getAccessToken());
        Assert.assertEquals("bearer", token.getTokenType());
        Assert.assertEquals(1661760660, token.getExpiresIn());
        Assert.assertEquals("9aba2490f1801af0", token.getRefreshToken());
        Assert.assertEquals("backend,backend.account", token.getScope());
        Assert.assertSame(true, token.hasRefreshToken());
    }

    @Test
    public void testParseWithoutScope() throws JsonProcessingException {
        String json = "{\"access_token\": \"eyJ0eXAiOiJKV1Qi\",\"token_type\": \"bearer\",\"expires_in\": 1661760660,\"refresh_token\": \"9aba2490f1801af0\"}";

        AccessToken token = (new ObjectMapper()).readValue(json, AccessToken.class);

        Assert.assertEquals("eyJ0eXAiOiJKV1Qi", token.getAccessToken());
        Assert.assertEquals("bearer", token.getTokenType());
        Assert.assertEquals(1661760660, token.getExpiresIn());
        Assert.assertEquals("9aba2490f1801af0", token.getRefreshToken());
        Assert.assertSame(null, token.getScope());
        Assert.assertSame(true, token.hasRefreshToken());
    }

    @Test
    public void testParseWithoutScopeAndRefreshToken() throws JsonProcessingException {
        String json = "{\"access_token\": \"eyJ0eXAiOiJKV1Qi\",\"token_type\": \"bearer\",\"expires_in\": 1661760660}";

        AccessToken token = (new ObjectMapper()).readValue(json, AccessToken.class);

        Assert.assertEquals("eyJ0eXAiOiJKV1Qi", token.getAccessToken());
        Assert.assertEquals("bearer", token.getTokenType());
        Assert.assertEquals(1661760660, token.getExpiresIn());
        Assert.assertSame(null, token.getRefreshToken());
        Assert.assertSame(null, token.getScope());
        Assert.assertSame(false, token.hasRefreshToken());
    }

}
