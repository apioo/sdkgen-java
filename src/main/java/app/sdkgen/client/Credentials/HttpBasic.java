package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;

public class HttpBasic implements CredentialsInterface {
    private final String userName;
    private final String password;

    public HttpBasic(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
