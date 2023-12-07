package Day_04;

import common.Read;

import java.util.List;

import static common.Read.read;

public class Main_Part_1 {

    private static int calculate(List<String> inputList) {

        int result = 0;

        for (String inputString : inputList) {

            String[] nSplit = inputString.split(": ")[1].split(" \\| ");

            String[] winArr = nSplit[0].trim().replaceAll(" +", " ").split(" ");
            String[] numArr = nSplit[1].trim().replaceAll(" +", " ").split(" ");

            int winCount = -1;

            for (String numStr : numArr) {

                int num = Integer.parseInt(numStr);

                for (String winStr : winArr) {

                    int winNum = Integer.parseInt(winStr);
                    if (num == winNum) winCount++;

                }

            }

            if (winCount != -1) result += (1 << winCount);

        }

        return result;

    }

    public static void main(String[] args) {

        double sTime, eTime;

        sTime = System.nanoTime();

        System.out.println(calculate(Read.read("Day_04", "input.txt")));

        eTime = System.nanoTime() - sTime;
        eTime /= 1_000_000.0d;

        System.out.println();
        System.out.println("Time Taken: " + eTime + "ms");

    }

}
