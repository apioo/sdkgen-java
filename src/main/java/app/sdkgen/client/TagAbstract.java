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
