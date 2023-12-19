package Day_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.Read.read;

public class Main_Part_2 {

    private static boolean sequenceIsAllZero(int[] sequenceArr) {
        return Arrays.stream(sequenceArr).allMatch(x -> x == 0);
    }

    private static int getPrediction(int[] reportArr) {

        List<int[]> historyList = new ArrayList<>();
        historyList.add(reportArr);

        int[] sequenceArrCurrent = reportArr;

        while (! sequenceIsAllZero(sequenceArrCurrent)) {

            int[] sequenceArrNext = new int[sequenceArrCurrent.length - 1];

            for (int i = 0; i < sequenceArrCurrent.length - 1; i++) {

                int xVal = sequenceArrCurrent[i];
                int yVal = sequenceArrCurrent[i + 1];

                int difference = yVal - xVal;

                sequenceArrNext[i] = difference;

            }

            historyList.add(sequenceArrNext);

            sequenceArrCurrent = historyList.getLast();

        }

        int accumulator = 0;

        for (int i = historyList.size() - 2; i >= 0; i--) {

            int[] historyArr = historyList.get(i + 1);
            accumulator = historyArr[0] - accumulator;

        }

        return reportArr[0] - accumulator;

    }

    private static int calculate(List<String> inputList) {

        int result = 0;

        List<int[]> reportList = new ArrayList<>();

        for (int inputListIndex = 0; inputListIndex < inputList.size(); inputListIndex++) {
            String[] inputListSplit = inputList.get(inputListIndex).trim().replaceAll(" +", " ").split(" ");
            reportList.add(new int[inputListSplit.length]);
            for (int i = 0; i < inputListSplit.length; i++) reportList.get(inputListIndex)[i] = Integer.parseInt(inputListSplit[i]);
        }

        for (int[] reportListArr : reportList) result += getPrediction(reportListArr);

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_09", "Input.TXT");

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
