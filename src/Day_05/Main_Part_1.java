package Day_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.Read.read;

public class Main_Part_1 {

    private static List<Long> getSeedList(List<String> inputList) {

        List<Long> seedList = new ArrayList<>();

        String[] inputStringSplit = inputList.getFirst().trim().replaceAll(" +", " ").split(": ")[1].split(" ");

        for (String seedStr : inputStringSplit) seedList.add(Long.parseLong(seedStr));

        return seedList;

    }

    private static List<String> getKeyList(List<String> inputList) {

        List<String> keyList = new ArrayList<>();

        for (String inputString : inputList) {

            if (inputString.isBlank()) continue;
            inputString = inputString.trim();

            if (Character.isAlphabetic(inputString.charAt(0))) {
                keyList.add(inputString.trim().substring(0, inputString.indexOf(" ")));
            }

        }

        return keyList;

    }

    private static Map<String, List<Long[]>> getAlmanacMap(List<String> inputList) {

        Map<String, List<Long[]>> almanacMap = new HashMap<>();

        String cKey = "";

        for (String inputString : inputList) {

            if (inputString.isBlank()) continue;
            inputString = inputString.trim();

            if (Character.isAlphabetic(inputString.charAt(0))) {
                cKey = inputString.trim().substring(0, inputString.indexOf(" "));
                almanacMap.put(cKey, new ArrayList<>());
            } else {
                String[] inputStringSplit = inputString.replaceAll(" +", " ").split(" ");
                almanacMap.get(cKey).add(new Long[inputStringSplit.length]);
                for (int cKeyIndex = 0; cKeyIndex < inputStringSplit.length; cKeyIndex++)
                    almanacMap.get(cKey).getLast()[cKeyIndex] = Long.parseLong(inputStringSplit[cKeyIndex]);
            }

        }

        return almanacMap;

    }

    private static long calculate(List<String> inputList) {

        long result = Integer.MAX_VALUE;

        List<Long> seedList = getSeedList(inputList);

        inputList.removeFirst();

        List<String> keyList = getKeyList(inputList);
        Map<String, List<Long[]>> almanacMap = getAlmanacMap(inputList);

        for (Long seedNum : seedList) {

            for (String mapKey : keyList) {

                for (Long[] mapArr : almanacMap.get(mapKey)) {

                    if (seedNum < mapArr[1]) continue;
                    if (seedNum > mapArr[1] + mapArr[2] - 1) continue;

                    seedNum += (mapArr[0] - mapArr[1]);
                    break;

                }

            }

            if (seedNum < result) result = seedNum;

        }

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_05", "Input.TXT");

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
