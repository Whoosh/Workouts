package project_euler.roblems_1_100.problem_46;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.util.ArrayList;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=46
 */

public class Goldbach implements Starter {

    private static final int LIMIT = 1000;
    private static ArrayList<Long> primes;
    private static ArrayList<Long> comb;

    public static void main(String[] args) {
        primes = new ArrayList<>(LIMIT);
        comb = new ArrayList<>(LIMIT);
        primes.add(2L);
        for (long i = 3; primes.size() != LIMIT; i += 2) {
            (CustomMathFunctions.isPrime(i) ? primes : comb).add(i);
        }
        new Goldbach().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        System.out.println(
                comb.stream()
                        .filter(val -> !comb(val))
                        .mapToLong(x -> x)
                        .unordered()
                        .parallel()
                        .min());
    }

    private static boolean comb(long val) {
        final int index = findFirstApproximatelyIndexOfPrime(val, 0, primes.size());
        return LongStream.rangeClosed(0, index)
                .map(x -> primes.get((int) x))
                .filter(x -> squareTest(val - x))
                .findFirst()
                .isPresent();
    }

    private static boolean squareTest(long number) {
        final double b = Math.sqrt(number / 2);
        return b == ((long) b);
    }

    private static int findFirstApproximatelyIndexOfPrime(long val, int i, int size) {
        if (size - i == 1) return i;
        final int mid = i + ((size - i) / 2);
        return (primes.get(mid) < val) ? findFirstApproximatelyIndexOfPrime(val, mid, size) : findFirstApproximatelyIndexOfPrime(val, 0, mid);
    }
}