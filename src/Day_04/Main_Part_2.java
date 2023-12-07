package Day_04;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static common.Read.read;

public class Main_Part_2 {

    private static int calculate(List<String> inputList) {

        int result = 0;

        Deque<String> inputQueue = new ArrayDeque<>(inputList);
        List<String> winList = new ArrayList<>();
        String inputString;

        while ((inputString = inputQueue.poll()) != null) {

            String[] nSplit = inputString.split(": ");

            int index = Integer.parseInt(nSplit[0].trim().replaceAll(" +", " ").split(" ")[1]) - 1;
            String[] numSection = nSplit[1].split(" \\| ");

            Set<String> winSet = Arrays.stream(numSection[0].trim().replaceAll(" +", " ").split(" ")).collect(Collectors.toSet());
            Set<String> numSet = Arrays.stream(numSection[1].trim().replaceAll(" +", " ").split(" ")).collect(Collectors.toSet());

            numSet.retainAll(winSet);

            int winCount = 0;

            for (int i = 0; i < numSet.size(); i++) winList.add(inputList.get(++winCount + index));

            if (winCount > 0) {
                winList.reversed().forEach(inputQueue::addFirst);
                winList.clear();
            }

            result++;

        }

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_04", "input.txt");

        double sTime, eTime;
        int answer;

        sTime = System.nanoTime();

        answer = calculate(inputList);

        eTime = System.nanoTime() - sTime;
        eTime /= 1_000_000.0d;

        System.out.println("Answer: " + answer);
        System.out.println();
        System.out.println("Time Taken: " + eTime + "ms");

    }

}
