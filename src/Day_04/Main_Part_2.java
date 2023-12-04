package Day_04;

import java.util.*;
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

        System.out.println(calculate(read("Day_04", "input.txt")));

    }

}
