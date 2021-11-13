package app.sdkgen.client.TokenStore;

import app.sdkgen.client.AccessToken;
import app.sdkgen.client.Exception.TokenPersistException;
import app.sdkgen.client.Exception.TokenReadException;
import app.sdkgen.client.TokenStoreInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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
    public AccessToken get() throws TokenReadException {
        try {
            List<String> lines = Files.readAllLines(this.getFileName().toPath());

            StringBuilder builder = new StringBuilder();
            for (String line : lines) {
                builder.append(line);
                builder.append("\n");
            }

            return (new ObjectMapper()).readValue(builder.toString(), AccessToken.class);
        } catch (IOException e) {
            throw new TokenReadException("Could not read token from file", e);
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

    private File getFileName()
    {
        return new File(this.cacheDir + "/" + this.fileName);
    }
}
