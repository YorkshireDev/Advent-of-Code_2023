package Day_01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String[] words =
            new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

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

            StringBuilder sentence = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {

                char current = input.charAt(i);
                sentence.append(current);

                if (Character.isDigit(current)) {

                    if (firstNum == null) firstNum = current;
                    lastNum = current;

                } else {

                    for (int wIndex = 0; wIndex < words.length; wIndex++) {

                        if (sentence.toString().contains(words[wIndex])) {

                            char wIndexToChar = (char) ('0' + (wIndex + 1));

                            if (firstNum == null) firstNum = wIndexToChar;
                            lastNum = wIndexToChar;

                            sentence = new StringBuilder().append(current);
                            break;

                        }

                    }

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