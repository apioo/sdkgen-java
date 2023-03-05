package app.sdkgen.client;

import app.sdkgen.client.Exception.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class Parser {
    private final String baseUrl;
    private final ObjectMapper objectMapper;
    private final SimpleDateFormat dateFormatter;
    private final SimpleDateFormat dateTimeFormatter;
    private final SimpleDateFormat timeFormatter;

    public Parser(String baseUrl, ObjectMapper objectMapper) {
        this.baseUrl = baseUrl;
        this.objectMapper = objectMapper;
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.timeFormatter = new SimpleDateFormat("HH:mm:ss");
    }

    public String url(String path, Map<String, Object> parameters) {
        return this.substituteParameters(this.baseUrl + "/" + path, parameters);
    }

    public <T> T parse(String data, Class<T> value) throws ParseException {
        try {
            return this.objectMapper.readValue(data, value);
        } catch (JsonProcessingException e) {
            throw new ParseException("The provided JSON data is invalid: " + e.getMessage(), e);
        }
    }

    public void query(URIBuilder builder, Map<String, Object> parameters) {
        parameters.forEach((key, value) -> {
            if (value instanceof String) {
                builder.addParameter(key, (String) value);
            } else if (value instanceof Integer) {
                builder.addParameter(key, String.valueOf(value));
            } else if (value instanceof Float) {
                builder.addParameter(key, String.valueOf(value));
            } else if (value instanceof Boolean) {
                builder.addParameter(key, String.valueOf(value));
            } else if (value instanceof LocalDate) {
                builder.addParameter(key, this.dateFormatter.format(value));
            } else if (value instanceof LocalDateTime) {
                builder.addParameter(key, this.dateTimeFormatter.format(value));
            } else if (value instanceof LocalTime) {
                builder.addParameter(key, this.timeFormatter.format(value));
            }
        });
    }

    private String substituteParameters(String url, Map<String, Object> parameters) {
        return url;
    }
}
