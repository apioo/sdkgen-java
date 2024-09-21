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

import app.sdkgen.client.Exception.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    private final String baseUrl;
    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter dateTimeFormatter;
    private final DateTimeFormatter timeFormatter;

    public Parser(String baseUrl, ObjectMapper objectMapper) {
        this.baseUrl = this.normalizeBaseUrl(baseUrl);
        this.objectMapper = objectMapper;
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public String url(String path, Map<String, Object> parameters) {
        return this.baseUrl + "/" + this.substituteParameters(path, parameters);
    }

    public <T> T parse(String data, Class<T> value) throws ParseException {
        try {
            return this.objectMapper.readValue(data, value);
        } catch (JsonProcessingException e) {
            throw new ParseException("The provided JSON data is invalid: " + e.getMessage(), e);
        }
    }

    public <T> T parse(String data, TypeReference<T> value) throws ParseException {
        try {
            return this.objectMapper.readValue(data, value);
        } catch (JsonProcessingException e) {
            throw new ParseException("The provided JSON data is invalid: " + e.getMessage(), e);
        }
    }

    public void query(URIBuilder builder, Map<String, Object> parameters) {
        this.query(builder, parameters, new ArrayList<>());
    }

    public void query(URIBuilder builder, Map<String, Object> parameters, List<String> structNames) {
        parameters.forEach((name, value) -> {
            if (value == null) {
                return;
            }

            if (structNames.contains(name)) {
                this.query(builder, this.objectMapper.convertValue(value, new TypeReference<>() {
                }));
            } else {
                builder.addParameter(name, this.toString(value));
            }
        });
    }

    public HttpReturn handle(int code, HttpEntity entity) {
        return new HttpReturn(code, entity);
    }

    private String substituteParameters(String path, Map<String, Object> parameters) {
        String[] parts = path.split("/");
        List<String> result = new ArrayList<>();

        for (String part : parts) {
            if (part == null || part.isBlank()) {
                continue;
            }

            String name = null;
            if (part.startsWith(":")) {
                name = part.substring(1);
            } else if (part.startsWith("$")) {
                int pos = part.indexOf("<");
                name = pos != -1 ? part.substring(1, pos) : part.substring(1);
            } else if (part.startsWith("{") && part.endsWith("}")) {
                name = part.substring(1, part.length() - 1);
            }

            if (name != null && parameters.containsKey(name)) {
                part = this.toString(parameters.get(name));
            }

            result.add(part);
        }

        return String.join("/", result);
    }

    private String toString(Object value) {
        if (value instanceof String) {
            return String.valueOf(value);
        } else if (value instanceof Float || value instanceof Double) {
            return String.valueOf(value);
        } else if (value instanceof Integer) {
            return String.valueOf(value);
        } else if (value instanceof Boolean) {
            return (Boolean) value ? "1" : "0";
        } else if (value instanceof LocalDate) {
            return ((LocalDate) value).format(this.dateFormatter);
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).format(this.dateTimeFormatter);
        } else if (value instanceof LocalTime) {
            return ((LocalTime) value).format(this.timeFormatter);
        } else {
            return "";
        }
    }

    private String normalizeBaseUrl(String baseUrl) {
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        return baseUrl;
    }

    static public class HttpReturn {
        private final int code;
        private final HttpEntity entity;

        private HttpReturn(final int code, final HttpEntity entity) {
            this.code = code;
            this.entity = entity;
        }

        public boolean isSuccessful() {
            return this.code >= 200 && this.code <= 299;
        }

        public int getCode() {
            return this.code;
        }

        public String getContent() throws IOException, ParseException {
            try {
                return EntityUtils.toString(this.entity);
            } catch (org.apache.hc.core5.http.ParseException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

        public byte[] getByteArray() throws IOException {
            return EntityUtils.toByteArray(this.entity);
        }
    }
}
