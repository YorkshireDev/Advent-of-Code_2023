package Day_08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.Read.read;

public class Main_Part_1 {

    private static int calculate(List<String> inputList) {

        int result = 0;

        String instructionPattern = inputList.getFirst();
        int instructionIndex = 0;
        int instructionLength = instructionPattern.length();

        Map<String, String[]> nodeMap = new HashMap<>();

        for (int i = 1; i < inputList.size(); i++) {

            String[] rootSplit = inputList.get(i).split(" = ");
            String[] elementSplit = rootSplit[1].replaceAll("[()]", "").split(", ");

            nodeMap.put(rootSplit[0], elementSplit);

        }

        String currentNode = "AAA";
        String nodeEnd = "ZZZ";

        while (!currentNode.equals(nodeEnd)) {

            char instruction = instructionPattern.charAt(instructionIndex++);
            currentNode = nodeMap.get(currentNode)[instruction == 'L' ? 0 : 1];

            if (instructionIndex == instructionLength) instructionIndex = 0;

            result++;

        }

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_08", "Input.TXT");

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