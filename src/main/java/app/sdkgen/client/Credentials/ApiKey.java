package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;

public class ApiKey implements CredentialsInterface {
    private final String token;
    private final String name;
    private final String in;

    public ApiKey(String token, String name, String in) {
        this.token = token;
        this.name = name;
        this.in = in;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public String getIn() {
        return in;
    }
}
