package project_euler.roblems_1_100.problem_77;

import project_euler.sub_code.Primes;

import java.util.stream.IntStream;

/**
 * Created by whoosh on 4/4/16.
 */
public class PrimeSummations {

    private static final int QUESTION = 5000;

    public static void main(String[] args) {
        System.out.println(countWithPrimeLimitOf(80));
    }

    private static int countWithPrimeLimitOf(int val) {
        int[] way = new int[val + 1];
        way[0] = 1;
        for (int prime : Primes.getPrimes(val).toArray()) {
            for (int j = prime; j <= val; j++) {
                way[j] += way[j - prime];
            }
        }
        return (int) IntStream.of(way).filter(x -> x < QUESTION).count();
    }
}
