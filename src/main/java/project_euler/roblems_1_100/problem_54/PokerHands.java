package project_euler.roblems_1_100.problem_54;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.FileFunctions;
import project_euler.sub_code.DefaultBenchmark;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * https://projecteuler.net/problem=53
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PokerHands {

    private static final String FILE_SOURCE = "/home/whoosh/IdeaProjects/ProjectEuler/src/main/java/problems_1_100/problem_54/hands.txt";
    private static final List<String> FILE_LINES = FileFunctions.getFileLines(FILE_SOURCE);

    public static void main(String[] args) {
        DefaultBenchmark.run(PokerHands.class);
        run();
    }

    // FIXME наговнокодил от бога! На форуме есть разбор насчёт +-3,

    @Benchmark
    public static void run() {
        long playerWinCount = FILE_LINES.stream().filter(PokerHands::isFirstPlayerWin).count() - 3;
       // System.out.println(playerWinCount);
    }

    private static boolean isFirstPlayerWin(String hands) {
        final String playerOne = hands.substring(0, 14);
        final String playerTwo = hands.substring(15, hands.length());
        return isFirstWin(playerOne, playerTwo);
    }

    private static boolean isFirstWin(String one, String two) {
        final Card[] cardsOne = parseHand(one);
        final Card[] cardsTwo = parseHand(two);
        Arrays.sort(cardsOne);
        Arrays.sort(cardsTwo);
        final Combination playerOneCombination = calculateTopOfCombination(cardsOne);
        final int pointsOne = playerOneCombination.ordinal();
        final int pointsTwo = calculateTopOfCombination(cardsTwo).ordinal();
        if (pointsOne == pointsTwo) {
            return isWinByRank(cardsOne, cardsTwo, playerOneCombination);
        }
        return pointsOne > pointsTwo;
    }

    private static boolean isWinByRank(Card[] cardsOne, Card[] cardsTwo, Combination combination) {
        switch (combination) {
            case ROYAL:
                return true;
            case FLUSH:
            case STRAIGHT:
            case STRAIGHT_FLUSH:
                return checkRankOfStraight(cardsOne, cardsTwo);
            case FOUR_OF_KIND:
                return checkRankOfFourKind(cardsOne, cardsTwo);
            case FULL_HOUSE:
                return checkRankOfFullHouseR(cardsOne, cardsTwo);
            case THREE_OF_KIND:
                return checkRankOfThreeOfKind(cardsOne, cardsTwo);
            case TWO_PAIR:
                return checkRankOfTwoPair(cardsOne, cardsTwo);
            case ONE_PAIR:
                return checkRankOfOnePair(cardsOne, cardsTwo);
            case HIGH:
                return checkRankOfHighCard(cardsOne, cardsTwo);
            default:
                return false;
        }
    }

    private static boolean checkRankOfHighCard(Card[] cardsOne, Card[] cardsTwo) {
        for (int i = 4; i >= 0; i--) {
            if (cardsOne[i].rank < cardsTwo[i].rank) return false;
            if (cardsOne[i].rank > cardsTwo[i].rank) return true;
        }
        return false;
    }

    private static boolean checkRankOfOnePair(Card[] cardsOne, Card[] cardsTwo) {
        final int[] mapOne = getMappedRank(cardsOne);
        final int[] mapTwo = getMappedRank(cardsTwo);
        final int pairRankOne = getPowerOfCardFromRightAndRemove(mapOne, 2);
        final int pairRankTwo = getPowerOfCardFromRightAndRemove(mapTwo, 2);
        for (int i = 0; i < cardsOne.length; i++) {
            if (cardsOne[i].rank == pairRankOne) cardsOne[i].rank = 0;
            if (cardsTwo[i].rank == pairRankTwo) cardsTwo[i].rank = 0;
        }
        if (pairRankOne == pairRankTwo) {
            return checkRankOfHighCard(cardsOne, cardsTwo);
        }
        return pairRankOne > pairRankTwo;
    }

    private static boolean checkRankOfTwoPair(Card[] cardsOne, Card[] cardsTwo) {
        int[] mapOne = getMappedRank(cardsOne);
        int[] mapTwo = getMappedRank(cardsTwo);
        final int firstPairRankOne = getPowerOfCardFromRight(mapOne, 2);
        final int firstPairRankTwo = getPowerOfCardFromRight(mapTwo, 2);
        if (firstPairRankOne == firstPairRankTwo) {
            final int nextPairRankOne = getPowerOfCardFromRight(mapOne, 2);
            final int nextPairRankTwo = getPowerOfCardFromRight(mapTwo, 2);
            if (nextPairRankOne == nextPairRankTwo) {
                final int lastKickerOne = getPowerOfCardFromLeft(mapOne, 1);
                final int lastKickerTwo = getPowerOfCardFromLeft(mapTwo, 1);
                return lastKickerOne > lastKickerTwo;
            }
            return nextPairRankOne > nextPairRankTwo;
        }
        return firstPairRankOne > firstPairRankTwo;
    }

    private static boolean checkRankOfThreeOfKind(Card[] cardsOne, Card[] cardsTwo) {
        final int[] mapOne = getMappedRank(cardsOne);
        final int[] mapTwo = getMappedRank(cardsTwo);
        final int threePowerOne = getPowerOfCardFromLeft(mapOne, 3);
        final int threePowerTwo = getPowerOfCardFromLeft(mapTwo, 3);
        if (threePowerOne == threePowerTwo) {
            final int firstKickerOne = getPowerOfCardFromRight(mapOne, 1);
            final int firstKickerTwo = getPowerOfCardFromRight(mapTwo, 1);
            if (firstKickerOne == firstKickerTwo) {
                final int lastKickerOne = getPowerOfCardFromLeft(mapOne, 1);
                final int lastKickerTwo = getPowerOfCardFromLeft(mapTwo, 1);
                return lastKickerOne > lastKickerTwo;
            }
            return firstKickerOne > firstKickerTwo;
        }
        return threePowerOne > threePowerTwo;
    }

    private static boolean checkRankOfFullHouseR(Card[] cardsOne, Card[] cardsTwo) {
        final int[] mapOne = getMappedRank(cardsOne);
        final int[] mapTwo = getMappedRank(cardsTwo);
        final int powerOfCardOne = getPowerOfCardFromLeft(mapOne, 3);
        final int powerOfCardTwo = getPowerOfCardFromLeft(mapTwo, 3);
        return powerOfCardOne > powerOfCardTwo || (powerOfCardOne
                == powerOfCardTwo && getPowerOfCardFromLeft(mapOne, 2) > getPowerOfCardFromLeft(mapTwo, 2));
    }

    private static int[] getMappedRank(Card[] cards) {
        final int[] map = new int[15];
        for (Card card : cards) ++map[card.rank];
        return map;
    }

    private static int getPowerOfCardFromRightAndRemove(int[] mapOne, int cardsCount) {
        for (int i = mapOne.length - 1; i >= 2; --i)
            if (mapOne[i] == cardsCount) {
                mapOne[i] = 0;
                return i;
            }
        return 0;
    }

    private static int getPowerOfCardFromLeft(int[] mapOne, int cardsCount) {
        for (int i = 2; i < mapOne.length; i++)
            if (mapOne[i] == cardsCount) return i;
        return 0;
    }

    private static int getPowerOfCardFromRight(int[] mapOne, int cardsCount) {
        for (int i = mapOne.length - 1; i >= 2; --i)
            if (mapOne[i] == cardsCount) return i;
        return 0;
    }

    private static boolean checkRankOfStraight(Card[] cardsOne, Card[] cardsTwo) {
        return cardsOne[4].rank > cardsTwo[4].rank;
    }

    private static boolean checkRankOfFourKind(Card[] cardsOne, Card[] cardsTwo) {
        final int rankOne = cardsOne[0].rank == cardsOne[1].rank ? cardsOne[4].rank : cardsOne[0].rank;
        final int rankTwo = cardsTwo[0].rank == cardsTwo[1].rank ? cardsTwo[4].rank : cardsTwo[0].rank;
        return cardsOne[2].rank == cardsTwo[2].rank ? rankOne > rankTwo : cardsOne[2].rank > cardsTwo[2].rank;
    }

    private static Combination calculateTopOfCombination(Card[] cards) {
        Combination combination = checkFlushBranch(cards);
        if (combination != Combination.EMPTY) return combination;
        combination = checkFourOfKind(cards);
        if (combination != Combination.EMPTY) return combination;
        combination = checkFullHouse(cards);
        if (combination != Combination.EMPTY) return combination;
        combination = checkStraight(cards);
        if (combination != Combination.EMPTY) return combination;
        combination = checkThree(cards);
        if (combination != Combination.EMPTY) return combination;
        combination = checkPairsBranch(cards);
        if (combination != Combination.EMPTY) return combination;
        return Combination.HIGH;
    }

    private static Combination checkPairsBranch(Card[] cards) {
        final HashSet<Card> cardz = new HashSet<>(Arrays.asList(cards));
        if (cardz.size() == 3) return Combination.TWO_PAIR;
        if (cardz.size() == 4) return Combination.ONE_PAIR;
        return Combination.EMPTY;
    }

    private static Combination checkThree(Card[] cards) {
        return getPowerOfCardFromLeft(getMappedRank(cards), 3) == 0 ? Combination.EMPTY : Combination.THREE_OF_KIND;
    }

    private static Combination checkStraight(Card[] cards) {
        return isStraight(cards) ? Combination.STRAIGHT : Combination.EMPTY;
    }

    private static Combination checkFullHouse(Card[] cards) {
        return new HashSet<>(Arrays.asList(cards)).size() == 2 ? Combination.FULL_HOUSE : Combination.EMPTY;
    }

    private static Combination checkFourOfKind(Card[] cards) {
        final int s = cards[0].rank == cards[1].rank ? 0 : 1;
        final int rank = cards[2].rank;
        for (int i = s; i < cards.length - (s == 0 ? 1 : 0); i++)
            if (rank != cards[i].rank) return Combination.EMPTY;
        return Combination.FOUR_OF_KIND;
    }

    private static Combination checkFlushBranch(Card[] cards) {
        if (!isFlush(cards)) return Combination.EMPTY;
        if (isStraightFromTenToA(cards)) return Combination.ROYAL;
        if (isStraight(cards)) return Combination.STRAIGHT_FLUSH;
        return Combination.FLUSH;
    }

    private static boolean isStraight(Card[] cards) {
        for (int i = 0; i < cards.length - 1; i++)
            if (cards[i].rank != cards[i + 1].rank + 1) return false;
        return true;
    }

    private static boolean isFlush(Card[] cards) {
        for (int i = 1; i < cards.length; i++)
            if (cards[0].type != cards[i].type) return false;
        return true;
    }

    private static boolean isStraightFromTenToA(Card[] cards) {
        return cards[4].rank == 14 && isStraight(cards);
    }

    private static Card[] parseHand(String hand) {
        final Card[] cards = new Card[5];
        for (int i = 0, k = 0; k < cards.length; k++) {
            cards[k] = new Card();
            cards[k].rank = getCard(i++, hand);
            cards[k].type = getType(i++, hand);
            i++;
        }
        return cards;
    }

    private static int getType(int i, String hand) {
        return hand.charAt(i);
    }

    private static int getCard(int i, String hand) {
        switch (hand.charAt(i)) {
            case 'A':
                return 14;
            case 'K':
                return 13;
            case 'Q':
                return 12;
            case 'J':
                return 11;
            case 'T':
                return 10;
            default:
                return Integer.valueOf(hand.substring(i, i + 1));
        }
    }

}
