package app.sdkgen.client.TokenStore;

import app.sdkgen.client.AccessToken;
import app.sdkgen.client.Exception.Authenticator.TokenPersistException;
import app.sdkgen.client.TokenStoreInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileTokenStore implements TokenStoreInterface {
    private final String cacheDir;
    private final String fileName;

    public FileTokenStore(String cacheDir, String fileName) {
        if (fileName == null) {
            fileName = "sdkgen_access_token";
        }

        this.cacheDir = cacheDir;
        this.fileName = fileName;
    }

    @Override
    public AccessToken get() {
        try {
            if (!this.getFileName().isFile()) {
                return null;
            }

            String json = Files.readString(this.getFileName().toPath());
            if (json == null || json.isEmpty()) {
                return null;
            }

            return (new ObjectMapper()).readValue(json, AccessToken.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void persist(AccessToken token) throws TokenPersistException {
        try {
            String json = new ObjectMapper().writeValueAsString(token);

            FileWriter file = new FileWriter(this.getFileName());
            file.write(json);
            file.close();
        } catch (IOException e) {
            throw new TokenPersistException("Could not persist token", e);
        }
    }

    @Override
    public void remove() {
        try {
            FileWriter file = new FileWriter(this.getFileName());
            file.write("");
            file.close();
        } catch (IOException e) {
        }
    }

    private File getFileName()
    {
        return new File(this.cacheDir + "/" + this.fileName);
    }
}
