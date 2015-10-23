package project_euler.roblems_1_100.problem_4;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * A palindromic number reads the same both ways. The largest isPalindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest isPalindrome made from the product of two 3-digit numbers.
 * https://projecteuler.net/problem=4
 */

public class LargePalindrome {

    private static final long N = 999;
    private static final long N_END = -(N / String.valueOf(N).length() + 1);

    public static final LongSupplier SEED = new LongSupplier() {

        private long max = N * N;

        @Override
        public long getAsLong() {
            do max--;
            while (!LongStream.rangeClosed(-N, N_END).filter(x -> max % x == 0 && Math.abs(max / x) < N).findFirst().isPresent());
            return max;
        }
    };

    public static void main(String[] args) {
        System.out.println(LongStream.generate(SEED).filter(x -> isPalindrome(x, 0)).findFirst());
    }

    private static boolean isPalindrome(long value, long r) {
        for (long i = value; i != 0; i = i / 10) r = r * 10 + i % 10;
        return r == value;
    }
}
