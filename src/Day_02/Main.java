package Day_02;

import java.util.List;

import static common.Read.read;

public class Main {

    private static int calculate(List<String> inputList, int[] bagContentArr) {

        int result = 0;

        for (int gameId = 0; gameId < inputList.size(); gameId++) {

            boolean possible = true;

            String[] subsetArr = inputList.get(gameId).split(": ")[1].split("; ");

            gameLoop:
            for (String subset : subsetArr) {

                String[] nCubeArr = subset.split(", ");

                for (String nCube : nCubeArr) {

                    String[] nCubeSplit = nCube.split(" ");
                    int bagIndex;

                    switch (nCubeSplit[1].charAt(0)) {
                        case 'r' -> bagIndex = 0;
                        case 'g' -> bagIndex = 1;
                        case 'b' -> bagIndex = 2;
                        default -> { continue; }
                    }

                    possible = Integer.parseInt(nCubeSplit[0]) <= bagContentArr[bagIndex];
                    if (! possible) break gameLoop;

                }

            }

            if (possible) result += (gameId + 1);

        }

        return result;

    }

    public static void main(String[] args) {

        int[] bagContentArr = new int[] { 12, 13, 14 }; // [ RED, GREEN, BLUE ]

        System.out.println(calculate(read("Day_02", "input.txt"), bagContentArr));

    }

}
