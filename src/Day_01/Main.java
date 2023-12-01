package Day_01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> read() throws IOException {

        String srcFile = System.getProperty("user.dir") +
                File.separator +
                "src" +
                File.separator +
                "Day_01" +
                File.separator +
                "input.txt";

        List<String> inputList = new ArrayList<>();
        String line;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFile));

        while ((line = bufferedReader.readLine()) != null) inputList.add(line);

        return inputList;

    }

    private static int calculate(List<String> inputList) {

        int result = 0;

        for (String input : inputList) {

            Character firstNum = null;
            Character lastNum = null;

            for (int i = 0; i < input.length(); i++) {

                char current = input.charAt(i);

                if (Character.isDigit(current)) {

                    if (firstNum == null) firstNum = current;
                    lastNum = current;

                }

            }

            if (firstNum == null) continue;

            result += Integer.parseInt(firstNum + String.valueOf(lastNum));

        }

        return result;

    }

    public static void main(String[] args) throws IOException {

        System.out.println(calculate(read()));

    }

}
