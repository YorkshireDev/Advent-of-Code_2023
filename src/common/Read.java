package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

        try { return Files.readAllLines(Path.of(fullPath)); }
        catch (IOException e) { throw new RuntimeException(e); }

    }

}
