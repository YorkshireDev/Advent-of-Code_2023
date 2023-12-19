package Day_07;

import java.util.*;

import static common.Read.read;

public class Main_Part_1 {

    private static final Map<Character, Integer> cardStrengthMap = new HashMap<>();

    private static Map<Character, Integer> getCardCountMap(String handToCheck) {

        Map<Character, Integer> cardCountMap = new HashMap<>();

        for (int i = 0; i < handToCheck.length(); i++) {

            char charAtIndex = handToCheck.charAt(i);

            if (cardCountMap.containsKey(charAtIndex)) cardCountMap.put(charAtIndex, cardCountMap.get(charAtIndex) + 1);
            else cardCountMap.put(charAtIndex, 1);

        }

        return cardCountMap;

    }

    private static void insertCardByRank(String handToInsert, List<String> typeList) {

        if (typeList.isEmpty()) {
            typeList.add(handToInsert);
            return;
        }

        boolean isInsert = false;

        for (int i = typeList.size() - 1; i >= 0; i--) {

            String handToCompare = typeList.get(i);

            for (int cIndex = 0; cIndex < handToCompare.length(); cIndex++) {

                char cHandInsert = handToInsert.charAt(cIndex);
                char cHandCompare = handToCompare.charAt(cIndex);

                if (cHandInsert == cHandCompare) continue;

                int cInsertWorth = cardStrengthMap.get(cHandInsert);
                int cCompareWorth = cardStrengthMap.get(cHandCompare);

                if (cInsertWorth > cCompareWorth) {
                    typeList.add(i + 1, handToInsert);
                    isInsert = true;
                }

                break;

            }

            if (isInsert) break;

        }

        if (! isInsert) typeList.addFirst(handToInsert);

    }

    private static boolean isFiveOfAKind(int sizeOfCardCountMap) {
        return sizeOfCardCountMap == 1;
    }

    private static boolean isFourOfAKind(Integer[] valueArr, int sizeOfCardCountMap) {
        return sizeOfCardCountMap == 2 && valueArr[0] == 1 && valueArr[1] == 4;
    }

    private static boolean isFullHouse(Integer[] valueArr, int sizeOfCardCountMap) {
        return sizeOfCardCountMap == 2 && valueArr[0] == 2 && valueArr[1] == 3;
    }

    private static boolean isThreeOfAKind(Integer[] valueArr, int sizeOfCardCountMap) {
        return sizeOfCardCountMap == 3 && valueArr[2] == 3;
    }

    private static boolean isTwoPair(Integer[] valueArr, int sizeOfCardCountMap) {
        return sizeOfCardCountMap == 3 && valueArr[1] == 2 && valueArr[2] == 2;
    }

    private static boolean isOnePair(Integer[] valueArr, int sizeOfCardCountMap) {
        return sizeOfCardCountMap == 4 && valueArr[3] == 2;
    }

    private static int calculate(List<String> inputList) {

        int result = 0;

        cardStrengthMap.put('A', 14);
        cardStrengthMap.put('K', 13);
        cardStrengthMap.put('Q', 12);
        cardStrengthMap.put('J', 11);
        cardStrengthMap.put('T', 10);
        cardStrengthMap.put('9', 9);
        cardStrengthMap.put('8', 8);
        cardStrengthMap.put('7', 7);
        cardStrengthMap.put('6', 6);
        cardStrengthMap.put('5', 5);
        cardStrengthMap.put('4', 4);
        cardStrengthMap.put('3', 3);
        cardStrengthMap.put('2', 2);

        Map<String, Integer> handBidMap = new HashMap<>();

        for (String inputString : inputList) {

            String[] inputStringSplit = inputString.trim().replaceAll(" +", " ").split(" ");
            handBidMap.put(inputStringSplit[0], Integer.parseInt(inputStringSplit[1]));

        }

        List<String> fiveOfAKindList = new ArrayList<>();
        List<String> fourOfAKindList = new ArrayList<>();
        List<String> fullHouseList = new ArrayList<>();
        List<String> threeOfAKindList = new ArrayList<>();
        List<String> twoPairList = new ArrayList<>();
        List<String> onePairList = new ArrayList<>();
        List<String> highCardList = new ArrayList<>();

        List<String> rankList = new ArrayList<>(inputList.size() * 2);

        for (String currentHand : handBidMap.keySet()) {

            Map<Character, Integer> cardCountMap = getCardCountMap(currentHand);
            int sizeOfCardCountMap = cardCountMap.size();

            if (isFiveOfAKind(sizeOfCardCountMap)) {
                insertCardByRank(currentHand, fiveOfAKindList);
                continue;
            }

            Integer[] valueArr = cardCountMap.values().stream().sorted().toArray(Integer[]::new);

            if (isFourOfAKind(valueArr, sizeOfCardCountMap)) insertCardByRank(currentHand, fourOfAKindList);
            else if (isFullHouse(valueArr, sizeOfCardCountMap)) insertCardByRank(currentHand, fullHouseList);
            else if (isThreeOfAKind(valueArr, sizeOfCardCountMap)) insertCardByRank(currentHand, threeOfAKindList);
            else if (isTwoPair(valueArr, sizeOfCardCountMap)) insertCardByRank(currentHand, twoPairList);
            else if (isOnePair(valueArr, sizeOfCardCountMap)) insertCardByRank(currentHand, onePairList);
            else insertCardByRank(currentHand, highCardList);

        }

        rankList.addAll(highCardList);
        rankList.addAll(onePairList);
        rankList.addAll(twoPairList);
        rankList.addAll(threeOfAKindList);
        rankList.addAll(fullHouseList);
        rankList.addAll(fourOfAKindList);
        rankList.addAll(fiveOfAKindList);

        for (int i = 0; i < rankList.size(); i++) result += ( handBidMap.get(rankList.get(i)) * (i + 1) );

        return result;

    }

    public static void main(String[] args) {

        List<String> inputList = read("Day_07", "Input.TXT");

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