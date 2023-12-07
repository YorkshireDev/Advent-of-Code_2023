package Day_03;

import common.Read;

import java.util.ArrayList;
import java.util.List;

import static common.Read.read;

public class Main_Part_2 {

    private static int[] getAdjPair(int inputListIndex, int gearIndex, List<String> inputList) {

        int[] adjPair = new int[] {-1, -1};

        int adjCount = 0;

        List<String> searchList = new ArrayList<>();

        if (inputListIndex > 0) searchList.add(inputList.get(inputListIndex - 1));
        searchList.add(inputList.get(inputListIndex));
        if (inputListIndex < inputList.size() - 1) searchList.add(inputList.get(inputListIndex + 1));

        for (String searchStr : searchList) {

            searchStr += ".";

            StringBuilder numBuilder = new StringBuilder();
            List<Integer> digitIndexList = new ArrayList<>();
            boolean isNum = false;

            for (int i = 0; i < searchStr.length(); i++) {

                char currentChar = searchStr.charAt(i);

                if (Character.isDigit(currentChar)) {

                    numBuilder.append(currentChar);
                    digitIndexList.add(i);
                    isNum = true;

                } else {

                    if (isNum) {

                        for (int dIndex : digitIndexList) {

                            if (dIndex == gearIndex - 1 || dIndex == gearIndex || dIndex == gearIndex + 1) {
                                adjPair[adjCount++] = Integer.parseInt(numBuilder.toString());
                                if (adjCount == 2) return adjPair;
                                break;
                            }

                        }

                        numBuilder = new StringBuilder();
                        digitIndexList.clear();
                        isNum = false;

                    }

                }

            }

        }

        return adjPair;

    }

    private static int calculate(List<String> inputList) {

        int result = 0;

        for (int inputListIndex = 0; inputListIndex < inputList.size(); inputListIndex++) {

            String inputString = inputList.get(inputListIndex);

            for (int charIndex = 0; charIndex < inputString.length(); charIndex++) {

                char currentChar = inputString.charAt(charIndex);

                if (currentChar == '*') {

                    int[] adjPair = getAdjPair(inputListIndex, charIndex, inputList);

                    if (adjPair[1] == -1) continue;

                    result += (adjPair[0] * adjPair[1]);

                }

            }

        }

        return result;

    }

    public static void main(String[] args) {

        double sTime, eTime;

        sTime = System.nanoTime();

        System.out.println(calculate(Read.read("Day_03", "input.txt")));

        eTime = System.nanoTime() - sTime;
        eTime /= 1_000_000.0d;

        System.out.println();
        System.out.println("Time Taken: " + eTime + "ms");

    }

}
