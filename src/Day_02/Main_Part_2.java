package Day_02;

import java.util.List;

import static common.Read.read;

public class Main_Part_2 {

    private static long calculate(List<String> inputList) {

        long result = 0L;

        for (String game : inputList) {

            String[] subsetArr = game.split(": ")[1].split("; ");

            long rLargest = 1L;
            long gLargest = 1L;
            long bLargest = 1L;

            for (String subset : subsetArr) {

                String[] nCubeArr = subset.split(", ");

                for (String nCube : nCubeArr) {

                    String[] nCubeSplit = nCube.split(" ");

                    long nCurrent = Integer.parseInt(nCubeSplit[0]);

                    switch (nCubeSplit[1].charAt(0)) {
                        case 'r' -> { if (nCurrent > rLargest) rLargest = nCurrent; }
                        case 'g' -> { if (nCurrent > gLargest) gLargest = nCurrent; }
                        case 'b' -> { if (nCurrent > bLargest) bLargest = nCurrent; }
                    }

                }

            }

            long powerSet = rLargest * gLargest * bLargest;

            result += powerSet;

        }

        return result;

    }

    public static void main(String[] args) {

        System.out.println(calculate(read("Day_02", "input.txt")));

    }

}