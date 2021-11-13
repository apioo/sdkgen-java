package app.sdkgen.client.Credentials;

import app.sdkgen.client.CredentialsInterface;

public class HttpBasic implements CredentialsInterface {
    private final String userName;
    private final String passsword;

    public HttpBasic(String userName, String passsword) {
        this.userName = userName;
        this.passsword = passsword;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasssword() {
        return passsword;
    }
}
