package common;

import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static common.EncryptDecryptInput.decrypt;

public class Read {

    /**
     * Read in a file into a list of strings. Each line of the file maps to an index in the list.
     * @param dayDir the day directory, e.g., "Day_01"
     * @param srcFile the file to read, e.g., "Input.TXT"
     * @return a list of strings
     */
    public static List<String> read(String dayDir, String srcFile) {

        String fullPath = System.getProperty("user.dir") +
                File.separator +
                "src" +
                File.separator +
                dayDir +
                File.separator +
                srcFile;

        Path path = Path.of(fullPath);

        if (! Files.exists(path)) {
            try {
                decrypt();
            } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeySpecException |
                     InvalidKeyException | InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            }
        }

        try { return Files.readAllLines(path); }
        catch (IOException e) { throw new RuntimeException(e); }

    }

}
