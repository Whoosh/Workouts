package project_euler.roblems_1_100.problem_12;

import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=12
 */

public class HighlyDivisible {

    private static final long N = 500;

    public static void main(String[] args) {
        System.out.println((LongStream.iterate(1, x -> ++x).map(x -> x * (x + 1) / 2).filter(HighlyDivisible::hasNDivisions).findFirst()));
    }

    private static boolean hasNDivisions(long x) {
        LongStream range = LongStream.range(-(long) Math.sqrt(x), 0).parallel();
        return ((x & 1) > 0 ? range.filter(y -> (y & 1) > 0) : range).filter(y -> x % y == 0).count() * 2 >= N;
    }
}
