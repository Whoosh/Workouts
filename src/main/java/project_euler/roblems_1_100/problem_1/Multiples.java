package project_euler.roblems_1_100.problem_1;

import java.util.stream.LongStream;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * https://projecteuler.net/problem=1
 */

public class Multiples {

    public static final long START = 1;
    public static final long END = 1000;

    public static void main(String[] args) {
        System.out.println(LongStream.range(START, END).filter(value -> value % 3 == 0 || value % 5 == 0).sum());
    }
}
