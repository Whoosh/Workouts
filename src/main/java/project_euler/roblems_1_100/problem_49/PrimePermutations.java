package project_euler.roblems_1_100.problem_49;

import project_euler.sub_code.Permutations;
import project_euler.sub_code.Primes;
import project_euler.sub_code.CustomMathFunctions;

import java.util.BitSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * https://projecteuler.net/problem=49
 */

public class PrimePermutations {

    private final static int START = 1000;
    private final static int END = 10000;
    private final static int STEP = 3330;
    private static final int SEQUENCE_SIZE = 3;

    private static final Predicate<Integer> RANGE = x -> x > START && x + STEP < END;

    public static void main(String[] args) {
        final Map<Integer, Set<Integer>> primePermutations = getPrimesStream(START, END)
                .boxed()
                .collect(Collectors.toMap(x -> x, Permutations::getPermutations));
        for (Integer integer : primePermutations.keySet()) {
            final Set<Integer> integers = primePermutations.get(integer)
                    .stream()
                    .filter(RANGE)
                    .filter(CustomMathFunctions::isPrime)
                    .collect(Collectors.toSet());
            if (integers.contains(integer + STEP) && integers.size() == SEQUENCE_SIZE) {
                System.out.println(integer + " " + integers);
            }
        }
    }

    public static IntStream getPrimesStream(int start, int end) {
        int[] primes = Primes.getPrimes((int) Math.sqrt(end)).toArray();
        BitSet checkedMap = new BitSet();
        checkedMap.set(start + 1, end, true);
        for (int i = 0, b; i < primes.length; i++) {
            b = end % primes[i];
            for (int j = b == start ? 0 : start + primes[i] - b; j < end; j += primes[i]) {
                checkedMap.set(j, false);
            }
        }
        return checkedMap.stream();
    }
}
