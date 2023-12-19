package Day_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static common.Read.read;

public class Main_Part_2 {

    private static CountDownLatch syncLatch;

    private static List<String> keyList;
    private static Map<String, List<Long[]>> almanacMap;

    /**
     *  My CPU is faster at brute forcing it than I am at coming up with a good solution ;)
     *  Took me 171926.769829ms or just short of 3 minutes on a 5800X3D :)
     */
    private static class VerySillySolution implements Runnable {

        private final long sInit;
        private final long sRange;

        private long result;

        private VerySillySolution(long sInit, long sRange) {

            this.sInit = sInit;
            this.sRange = sRange;

        }

        public void setResult(long result) { this.result = result;}
        public long getResult() { return result; }

        @Override
        public void run() {

            result = Long.MAX_VALUE;

            long sNumCount = sInit;
            long sIncrement = 0;
            long sEnd = sInit + sRange;

            while (sNumCount < sEnd) {

                long seedNum = sInit + sIncrement;

                for (String mapKey : keyList) {

                    for (Long[] mapArr : almanacMap.get(mapKey)) {

                        if (seedNum < mapArr[1]) continue;
                        if (seedNum > mapArr[1] + mapArr[2] - 1) continue;

                        seedNum += (mapArr[0] - mapArr[1]);
                        break;

                    }

                }

                if (seedNum < getResult()) setResult(seedNum);

                sNumCount++;
                sIncrement++;

            }

            syncLatch.countDown();

        }

    }

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

        long result = Long.MAX_VALUE;

        List<Long> seedList = getSeedList(inputList);

        inputList.removeFirst();

        keyList = getKeyList(inputList);
        almanacMap = getAlmanacMap(inputList);

        int threadCount = Runtime.getRuntime().availableProcessors();

        try (ExecutorService threadPool = Executors.newFixedThreadPool(threadCount)) {

            VerySillySolution[] verySillySolutionArr = new VerySillySolution[seedList.size() / 2];

            syncLatch = new CountDownLatch(verySillySolutionArr.length);

            int verySillyIndex = 0;

            for (int i = 0; i < seedList.size(); i += 2)
                verySillySolutionArr[verySillyIndex++] = new VerySillySolution(seedList.get(i), seedList.get(i + 1));

            for (VerySillySolution verySillySolution : verySillySolutionArr) threadPool.submit(verySillySolution);

            syncLatch.await();

            for (VerySillySolution verySillySolution : verySillySolutionArr) {
                result = Math.min(result, verySillySolution.getResult());
            }

        } catch (InterruptedException e) { throw new RuntimeException(e); }

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
