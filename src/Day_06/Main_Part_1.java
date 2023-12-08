package Day_06;

import java.util.Arrays;
import java.util.List;

import static common.Read.read;

public class Main_Part_1 {

    private static int calculate(List<String> inputList) {

        int result = 1;

        int[] timeArr = Arrays.stream(inputList.getFirst().trim().replaceAll(" +", " ").split(": ")[1].split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] distanceArr = Arrays.stream(inputList.getLast().trim().replaceAll(" +", " ").split(": ")[1].split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < timeArr.length; i++) {

            int raceTime = timeArr[i];
            int recordDistance = distanceArr[i];

            int wCount = 0;

            for (int timeIncrement = 1; timeIncrement < raceTime - 1; timeIncrement++) {

                int raceDistance = timeIncrement * (raceTime - timeIncrement);

                if (raceDistance > recordDistance) wCount++;

            }

            if (wCount > 0) result *= wCount;

        }

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_06", "input.txt");

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
