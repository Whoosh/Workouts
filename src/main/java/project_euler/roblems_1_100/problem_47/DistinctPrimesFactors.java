package project_euler.roblems_1_100.problem_47;

import project_euler.sub_code.Primes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=47
 */

public class DistinctPrimesFactors {

    public static final int N = 500000;
    public static final int START = 2;
    public static final int FACTORS = 4;
    public static final int NUMBERS = 4;
    private static List<Integer> primes;

    public static void main(String[] args) {
        primes = Primes.getPrimes(N).boxed().collect(Collectors.toList());
        System.out.println(IntStream
                .range(START, N)
                .filter(x -> hasFactorz(x, FACTORS))
                .parallel()
                .findFirst());
    }

    private static boolean hasFactorz(int x, int factors) {
        for (int i = x; i < x + NUMBERS; i++)
            if (!hasFactors(i, factors)) return false;
        return true;
    }

    private static boolean hasFactors(long val, int nFactors) {
        return primes
                .stream()
                .filter(x -> x < (val / 2) + 1)
                .filter(x -> val % x == 0)
                .count() == nFactors;
    }
}
