package common;

import java.util.List;

public class Template {

    private static int calculate(List<String> inputList) {

        int result = 0;

        return result;

    }

    public static void main(String[] args) {

        double sTime, eTime;

        sTime = System.nanoTime();

        System.out.println(calculate(Read.read("Day_XX", "input.txt")));

        eTime = System.nanoTime() - sTime;
        eTime /= 1_000_000.0d;

        System.out.println();
        System.out.println("Time Taken: " + eTime + "ms");

    }

}
