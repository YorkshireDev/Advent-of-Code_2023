package Day_03;

import java.util.ArrayList;
import java.util.List;

import static common.Read.read;

public class Main_Part_1 {

    private static boolean isValidSymbol(char n) {
        return !Character.isDigit(n) && !Character.isAlphabetic(n) && n != '.';
    }

    private static boolean isPartNumber(int i, List<String> inputList, String inputString, List<Integer> digitIndexList) {

        for (int digitIndex : digitIndexList) {

            boolean isPartNumber =
                    (i > 0 && isValidSymbol(inputList.get(i - 1).charAt(digitIndex))) ||                                                                 // NORTH
                    (digitIndex < inputString.length() - 1 && isValidSymbol(inputString.charAt(digitIndex + 1))) ||                                      // EAST
                    (i < inputList.size() - 1 && isValidSymbol(inputList.get(i + 1).charAt(digitIndex))) ||                                              // SOUTH
                    (digitIndex > 0 && isValidSymbol(inputString.charAt(digitIndex - 1))) ||                                                             // WEST
                    (i > 0 && digitIndex < inputString.length() - 1 && isValidSymbol(inputList.get(i - 1).charAt(digitIndex + 1))) ||                    // NORTH-EAST
                    (i > 0 && digitIndex > 0 && isValidSymbol(inputList.get(i - 1).charAt(digitIndex - 1))) ||                                           // NORTH-WEST
                    (i < inputList.size() - 1 && digitIndex < inputString.length() - 1 && isValidSymbol(inputList.get(i + 1).charAt(digitIndex + 1))) || // SOUTH-EAST
                    (i < inputList.size() - 1 && digitIndex > 0 && isValidSymbol(inputList.get(i + 1).charAt(digitIndex - 1)));                          // SOUTH-WEST

            if (isPartNumber) return true;

        }

        return false;

    }

    private static int calculate(List<String> inputList) {

        int result = 0;

        for (int i = 0; i < inputList.size(); i++) {

            String inputString = inputList.get(i);

            StringBuilder numBuilder = new StringBuilder();
            List<Integer> digitIndexList = new ArrayList<>();

            boolean isNum = false;

            for (int cIndex = 0; cIndex < inputString.length(); cIndex++) {

                char iChar = inputString.charAt(cIndex);

                if (Character.isDigit(iChar)) {

                    numBuilder.append(iChar);
                    digitIndexList.add(cIndex);
                    isNum = true;

                } else {

                    if (isNum) {

                        if (isPartNumber(i, inputList, inputString, digitIndexList))
                            result += Integer.parseInt(numBuilder.toString());

                        numBuilder = new StringBuilder();
                        digitIndexList.clear();
                        isNum = false;

                    }

                }

            }

            if (!numBuilder.isEmpty() && isPartNumber(i, inputList, inputString, digitIndexList))
                result += Integer.parseInt(numBuilder.toString());

        }

        return result;

    }

    public static void main(String[] args) {

        System.out.println(calculate(read("Day_03", "input.txt")));

    }

}
