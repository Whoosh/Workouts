package project_euler.roblems_1_100.problem_51;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.Primes;

import java.math.BigInteger;
import java.util.Optional;

/**
 * https://projecteuler.net/problem=51
 */

public class PrimeReplacements {

    private static final BigInteger START = new BigInteger("100");
    private static final BigInteger STEP = new BigInteger("200000");
    private static final int NEIGHBOR_COUNT = 8;
    private static final int MARK = -10;

    public static void main(String[] args) {
        Optional<BigInteger> result = tryToFind(START, STEP);
        BigInteger step = STEP;
        while (!result.isPresent()) {
            result = tryToFind(step, step = step.add(STEP));
        }
        System.out.println(result);
    }

    private static Optional<BigInteger> tryToFind(BigInteger min, BigInteger max) {
        return Primes.bigPrimes(min, max)
                .filter(PrimeReplacements::hasEqualsDigits)
                .filter(PrimeReplacements::hasNeighbor)
                .findFirst();
    }

    private static boolean hasNeighbor(BigInteger val) {
        final Integer[] integers = CustomMathFunctions.numberToArray(val.intValue());
        int neighbor = 0;
        for (int i = 1; i < 10; i++) {
            if (isNeighborPrime(integers, i)) neighbor++;
        }
        return neighbor == NEIGHBOR_COUNT;
    }

    private static boolean isNeighborPrime(Integer[] integers, int next) {
        for (int i = 0; i < integers.length; i++) {
            for (int j = i + 1; j < integers.length - 1; j++) {
                if (integers[i].equals(integers[j])) {
                    return swapAll(integers, integers[i], next);
                }
            }
        }
        return false;
    }

    private static boolean swapAll(Integer[] integers, int digit, int next) {
        final Integer[] clone = integers.clone();
        for (int i = 0; i < clone.length; i++) {
            if (clone[i] == digit) clone[i] = next;
        }
        return CustomMathFunctions.isPrime(CustomMathFunctions.toValue(clone));
    }

    private static boolean hasEqualsDigits(BigInteger val) {
        final Integer[] integers = CustomMathFunctions.numberToArray(val.intValue());
        final int[] map = new int[10];
        for (int i = 0; i < integers.length; i++) {
            if (map[i] == MARK) return true;
            map[integers[i]] = MARK;
        }
        return false;
    }
}
