package common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Read<T> {

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

        List<String> inputList = new ArrayList<>();
        String line;

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fullPath));

            while ((line = bufferedReader.readLine()) != null) inputList.add(line);

        } catch (IOException e) { throw new RuntimeException(e); }

        return inputList;

    }

}
