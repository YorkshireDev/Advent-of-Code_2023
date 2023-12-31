package Day_01;

import java.util.List;

import static common.Read.read;

public class Main_Part_1_Part_2 {

    private static final String[] words =
            new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

    private static int calculate(List<String> inputList) {

        int result = 0;

        for (String input : inputList) {

            Character firstNum = null;
            Character lastNum = null;

            StringBuilder sentence = new StringBuilder();

            for (int i = 0; i < input.length(); i++) {

                char current = input.charAt(i);

                if (Character.isDigit(current)) {

                    if (firstNum == null) firstNum = current;
                    lastNum = current;

                } else {

                    sentence.append(current);

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

    public static void main(String[] args) {

        List<String> inputList = read("Day_01", "Input.TXT");

        double sTime, eTime;
        int answer;

        sTime = System.nanoTime();

        answer = calculate(inputList);

        eTime = System.nanoTime() - sTime;
        eTime /= 1_000_000.0d;

        System.out.println("Answer: " + answer);
        System.out.println("Time Taken: " + eTime + "ms");

    }

}