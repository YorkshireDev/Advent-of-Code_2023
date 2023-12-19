package Day_08;

import java.util.*;

import static common.Read.read;

public class Main_Part_2 {

    private static long getGreatestCommonDividerBetweenTwoNumbers(long nOne, long nTwo) {
        return (nTwo == 0L) ? nOne : getGreatestCommonDividerBetweenTwoNumbers(nTwo, nOne % nTwo);
    }

    private static long getGreatestCommonDividerFromSeriesOfNumbers(long... numArr) {
        return Arrays.stream(numArr).reduce(0L, Main_Part_2::getGreatestCommonDividerBetweenTwoNumbers);
    }

    private static long getLowestCommonMultipleFromSeriesOfNumbers(long[] numArr) {
        return Arrays.stream(numArr).reduce(1L, (numOne, numTwo) -> numOne * (numTwo / getGreatestCommonDividerFromSeriesOfNumbers(numOne, numTwo)));
    }

    private static long calculate(List<String> inputList) {

        int stepCount = 1;

        String instructionPattern = inputList.getFirst();
        int instructionIndex = 1;
        int instructionLength = instructionPattern.length();

        Map<String, String[]> nodeMap = new HashMap<>();

        List<String> trackList = new ArrayList<>();
        Map<String, String> trackMap = new HashMap<>();

        for (int i = 1; i < inputList.size(); i++) {

            String[] rootSplit = inputList.get(i).split(" = ");
            String[] elementSplit = rootSplit[1].replaceAll("[()]", "").split(", ");

            nodeMap.put(rootSplit[0], elementSplit);

            if (rootSplit[0].endsWith("A")) {

                trackList.add(rootSplit[0]);
                trackMap.put(rootSplit[0], elementSplit[instructionPattern.charAt(0) == 'L' ? 0 : 1]);

            }

        }

        long[] nodeStepArr = new long[trackList.size()];
        int nodeStepIndex = 0;

        boolean allNodesEndWithZ = false;

        while (! allNodesEndWithZ) {

            char instruction = instructionPattern.charAt(instructionIndex++);

            for (String trackNode : trackList) {
                trackMap.put(trackNode, nodeMap.get(trackMap.get(trackNode))[instruction == 'L' ? 0 : 1]);
            }

            if (instructionIndex == instructionLength) instructionIndex = 0;

            stepCount++;

            for (int i = trackList.size() - 1; i >= 0; i--) {
                String trackNode = trackList.get(i);
                if (trackMap.get(trackNode).endsWith("Z")) {
                    trackList.remove(i);
                    nodeStepArr[nodeStepIndex++] = stepCount;
                }
            }

            allNodesEndWithZ = trackList.isEmpty();

        }

        return getLowestCommonMultipleFromSeriesOfNumbers(nodeStepArr);

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_08", "input.txt");

        double sTime, eTime;
        long answer;

        sTime = System.nanoTime();

        answer = calculate(inputList);

        eTime = System.nanoTime() - sTime;
        eTime /= 1_000_000.0d;

        System.out.println("Answer: " + answer);
        System.out.println("Time Taken: " + eTime + "ms");

    }

}