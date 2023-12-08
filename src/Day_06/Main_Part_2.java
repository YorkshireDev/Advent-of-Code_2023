package Day_06;

import java.util.List;

import static common.Read.read;

public class Main_Part_2 {

    private static long calculate(List<String> inputList) {

        long result = 1L;

        String[] timeArr = inputList.getFirst().trim().replaceAll(" +", " ").split(": ")[1].split(" ");
        String[] distanceArr = inputList.getLast().trim().replaceAll(" +", " ").split(": ")[1].split(" ");

        long raceTime = Long.parseLong(String.join("", timeArr));
        long recordDistance = Long.parseLong(String.join("", distanceArr));

        long wCount = 0L;

        for (int timeIncrement = 1; timeIncrement < raceTime - 1; timeIncrement++) {

            long raceDistance = timeIncrement * (raceTime - timeIncrement);

            if (raceDistance > recordDistance) wCount++;

        }

        result *= wCount;

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_06", "input.txt");

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
